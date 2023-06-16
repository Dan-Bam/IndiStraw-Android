package com.danbam.presentation.ui.auth.find.find_password

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.R
import com.danbam.presentation.ui.auth.navigation.AuthNavigationItem
import com.danbam.presentation.ui.main.navigation.MainNavigationItem
import com.danbam.presentation.ui.profile.navigation.ProfileNavigationItem
import com.danbam.presentation.util.android.observeWithLifecycle
import com.danbam.presentation.util.view.popBackStack
import com.danbam.presentation.util.view.requestFocus
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class, ExperimentalComposeUiApi::class)
@Composable
fun FindPasswordScreen(
    navController: NavController,
    findPasswordViewModel: FindPasswordViewModel = hiltViewModel(),
    phoneNumber: String,
    isFindPassword: Boolean,
) {
    val container = findPasswordViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var checkPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var checkPasswordVisible by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = remember { FocusRequester() }
    val rePasswordFocusRequester = remember { FocusRequester() }

    val errorList = mapOf(
        FindPasswordSideEffect.EmptyPasswordException to stringResource(id = R.string.require_change_password),
        FindPasswordSideEffect.EmptyRePasswordException to stringResource(id = R.string.require_check_password),
        FindPasswordSideEffect.EmptyRePasswordException to stringResource(id = R.string.wrong_different_password),
        FindPasswordSideEffect.LengthPasswordException to stringResource(id = R.string.wrong_length_password),
        FindPasswordSideEffect.MatchPasswordException to stringResource(id = R.string.wrong_match_password),
        FindPasswordSideEffect.FailChangeException to stringResource(id = R.string.wait)
    )

    sideEffect.observeWithLifecycle {
        when (it) {
            is FindPasswordSideEffect.EmptyPasswordException, FindPasswordSideEffect.DifferentPasswordException, FindPasswordSideEffect.LengthPasswordException, FindPasswordSideEffect.MatchPasswordException, FindPasswordSideEffect.FailChangeException -> {
                passwordFocusRequester.requestFocus(keyboardController = keyboardController)
                errorText = errorList[it]!!
            }

            is FindPasswordSideEffect.EmptyRePasswordException -> {
                rePasswordFocusRequester.requestFocus(keyboardController = keyboardController)
                errorText = errorList[it]!!
            }

            is FindPasswordSideEffect.SuccessChange -> {
                keyboardController?.hide()
                if (isFindPassword) {
                    navController.navigate(AuthNavigationItem.Login.route) {
                        popUpTo(MainNavigationItem.Intro.route)
                    }
                } else {
                    navController.navigate(ProfileNavigationItem.Setting.route) {
                        popUpTo(ProfileNavigationItem.Profile.route)
                    }
                }
            }
        }
    }

    IndiStrawColumnBackground(
        onClickAction = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    ) {
        IndiStrawHeader(pressBackBtn = {
            navController.popBackStack(keyboardController = keyboardController)
        })
        HeadLineBold(
            modifier = Modifier
                .padding(start = 32.dp, top = 16.dp),
            text = stringResource(id = R.string.change_password)
        )
        IndiStrawTextField(
            modifier = Modifier
                .padding(top = 66.dp)
                .focusRequester(focusRequester = passwordFocusRequester),
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
            modifier = Modifier
                .padding(top = 20.dp)
                .focusRequester(focusRequester = rePasswordFocusRequester),
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
            color = IndiStrawTheme.colors.red,
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
        }
    }
}