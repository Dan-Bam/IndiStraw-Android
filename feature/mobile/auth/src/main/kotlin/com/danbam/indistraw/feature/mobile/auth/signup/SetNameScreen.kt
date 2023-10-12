package com.danbam.indistraw.feature.mobile.auth.signup

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.HeadLineBold
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.component.IndiStrawTextField
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.util.androidx.getActivity
import com.danbam.indistraw.core.design_system.util.androidx.observeWithLifecycle
import com.danbam.indistraw.core.design_system.util.androidx.popBackStack
import com.danbam.indistraw.core.design_system.util.androidx.requestFocus
import com.danbam.indistraw.feature.mobile.navigation.auth.AuthDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.auth.AuthNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.auth.CertificateType
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SetNameScreen(
    navController: NavController,
    signUpViewModel: SignUpViewModel = hiltViewModel(getActivity()),
) {
    val container = signUpViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var name by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val nameFocusRequester = remember { FocusRequester() }

    val errorList = mapOf(
        SignUpSideEffect.EmptyNameException to stringResource(id = R.string.require_name),
    )

    sideEffect.observeWithLifecycle {
        when (it) {
            is SignUpSideEffect.EmptyNameException -> {
                nameFocusRequester.requestFocus(keyboardController = keyboardController)
                errorText = errorList[it]!!
            }

            is SignUpSideEffect.Next -> {
                keyboardController?.hide()
                navController.navigate(AuthNavigationItem.Certificate.route + AuthDeepLinkKey.CERTIFICATE_TYPE + CertificateType.SIGN_UP)
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
            text = stringResource(id = R.string.require_name)
        )
        IndiStrawTextField(
            modifier = Modifier
                .padding(top = 96.dp)
                .focusRequester(focusRequester = nameFocusRequester),
            hint = stringResource(id = R.string.name),
            value = name,
            onValueChange = {
                if (errorText.isNotEmpty()) errorText = ""
                name = it
            })
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 7.dp),
            text = errorText,
            color = IndiStrawTheme.colors.red,
            fontSize = 12
        )
        IndiStrawButton(
            modifier = Modifier.padding(top = 78.dp),
            text = stringResource(id = R.string.next)
        ) {
            signUpViewModel.setName(name = name)
        }
    }
}