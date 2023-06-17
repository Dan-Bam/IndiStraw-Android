package com.danbam.presentation.ui.auth.signup

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
import androidx.compose.ui.unit.dp
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
import com.danbam.presentation.util.android.observeWithLifecycle
import com.danbam.presentation.util.view.popBackStack
import com.danbam.presentation.util.view.requestFocus
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SetIdScreen(
    navController: NavController,
    signUpViewModel: SignUpViewModel,
) {
    val container = signUpViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var id by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val idFocusRequester = remember { FocusRequester() }

    val errorList = mapOf(
        SignUpSideEffect.EmptyIdException to stringResource(id = R.string.require_id),
        SignUpSideEffect.MatchIdException to stringResource(id = R.string.wrong_length_id),
        SignUpSideEffect.EnrollIdException to stringResource(id = R.string.wrong_enroll_id)
    )

    sideEffect.observeWithLifecycle {
        when (it) {
            is SignUpSideEffect.EmptyIdException, SignUpSideEffect.MatchIdException, SignUpSideEffect.EnrollIdException -> {
                idFocusRequester.requestFocus(keyboardController = keyboardController)
                errorText = errorList[it]!!
            }

            is SignUpSideEffect.Next -> {
                keyboardController?.hide()
                navController.navigate(AuthNavigationItem.SetPassword.route)
            }

            else -> {

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
            text = stringResource(id = R.string.require_id)
        )
        IndiStrawTextField(
            modifier = Modifier
                .padding(top = 96.dp)
                .focusRequester(focusRequester = idFocusRequester),
            hint = stringResource(id = R.string.id),
            value = id,
            onValueChange = {
                if (errorText.isNotEmpty()) errorText = ""
                id = it
            })
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, top = 7.dp),
            text = errorText,
            color = IndiStrawTheme.colors.red,
            fontSize = 12
        )
        IndiStrawButton(
            modifier = Modifier.padding(top = 78.dp),
            text = stringResource(id = R.string.next)
        ) {
            signUpViewModel.setId(id = id)
        }
    }
}