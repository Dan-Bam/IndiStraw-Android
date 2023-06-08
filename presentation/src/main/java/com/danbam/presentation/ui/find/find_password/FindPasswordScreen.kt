package com.danbam.presentation.ui.find.find_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.component.TitleRegular
import com.danbam.presentation.R
import com.danbam.presentation.ui.login.LoginSideEffect
import com.danbam.presentation.util.AppNavigationItem
import com.danbam.presentation.util.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun FindPasswordScreen(
    navController: NavController,
    findPasswordViewModel: FindPasswordViewModel = hiltViewModel(),
    phoneNumber: String,
) {
    val container = findPasswordViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var checkPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var checkPasswordVisible by remember { mutableStateOf(false) }

    val errorList = mapOf(
        FindPasswordSideEffect.EmptyException to stringResource(id = R.string.require_change_password),
        FindPasswordSideEffect.DifferentException to stringResource(id = R.string.wrong_different_password),
        FindPasswordSideEffect.LengthException to stringResource(id = R.string.wrong_password_length),
        FindPasswordSideEffect.MatchException to stringResource(id = R.string.wrong_match_password),
        FindPasswordSideEffect.FailChangeException to stringResource(id = R.string.wait)
    )

    sideEffect.observeWithLifecycle {
        when (it) {
            is FindPasswordSideEffect.EmptyException, FindPasswordSideEffect.DifferentException, FindPasswordSideEffect.LengthException, FindPasswordSideEffect.MatchException, FindPasswordSideEffect.FailChangeException -> {
                errorText = errorList[it]!!
            }

            is FindPasswordSideEffect.SuccessChange -> {

            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        IndiStrawHeader(marginTop = 25, backStringId = R.string.back, pressBackBtn = {
            navController.popBackStack()
        })
        HeadLineBold(
            modifier = Modifier
                .padding(start = 32.dp, top = 16.dp),
            text = stringResource(id = R.string.change_password)
        )
        IndiStrawTextField(
            modifier = Modifier.padding(top = 66.dp),
            hint = stringResource(id = R.string.password),
            value = password,
            onValueChange = {
                if (errorText.isNotEmpty()) errorText = ""
                password = it
            },
            imeAction = ImeAction.Next,
            isToggleVisible = passwordVisible,
            onToggleChange = { passwordVisible = !passwordVisible }
        )
        IndiStrawTextField(
            modifier = Modifier.padding(top = 20.dp),
            hint = stringResource(id = R.string.check_password),
            value = checkPassword,
            onValueChange = {
                if (errorText.isNotEmpty()) errorText = ""
                checkPassword = it
            },
            isToggleVisible = checkPasswordVisible,
            onToggleChange = { checkPasswordVisible = !checkPasswordVisible }
        )
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, top = 7.dp),
            text = errorText,
            color = IndiStrawTheme.colors.error,
            fontSize = 12
        )
        IndiStrawButton(
            modifier = Modifier.padding(top = 37.dp),
            text = stringResource(id = R.string.check)
        ) {
            findPasswordViewModel.changePassword(
                phoneNumber = phoneNumber,
                password = password,
                checkPassword = checkPassword
            )
            navController.navigate(AppNavigationItem.Login.route) {
                popUpTo(AppNavigationItem.Intro.route)
            }
        }
    }
}