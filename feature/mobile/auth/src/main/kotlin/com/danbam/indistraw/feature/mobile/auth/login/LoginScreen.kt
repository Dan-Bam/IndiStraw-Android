package com.danbam.indistraw.feature.mobile.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.ExampleTextRegular
import com.danbam.indistraw.core.design_system.component.HeadLineBold
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.component.IndiStrawTextField
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.util.androidx.observeWithLifecycle
import com.danbam.indistraw.core.design_system.util.androidx.popBackStack
import com.danbam.indistraw.core.design_system.util.androidx.requestFocus
import com.danbam.indistraw.feature.mobile.navigation.auth.AuthDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.auth.AuthNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.auth.CertificateType
import com.danbam.indistraw.feature.mobile.navigation.main.MainNavigationItem
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val container = loginViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(true) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = remember { FocusRequester() }
    val idFocusRequester = remember { FocusRequester() }

    val errorList = mapOf(
        LoginSideEffect.EmptyIdException to stringResource(id = R.string.require_id),
        LoginSideEffect.EmptyPasswordException to stringResource(id = R.string.require_password),
        LoginSideEffect.MatchIdException to stringResource(id = R.string.wrong_id),
        LoginSideEffect.MatchPasswordException to stringResource(id = R.string.wrong_password)
    )

    sideEffect.observeWithLifecycle {
        when (it) {
            is LoginSideEffect.LoginSuccess -> {
                keyboardController?.hide()
                navController.navigate(MainNavigationItem.Main.route)
            }

            is LoginSideEffect.EmptyIdException, LoginSideEffect.MatchIdException -> {
                idFocusRequester.requestFocus(keyboardController = keyboardController)
                errorText = errorList[it]!!
            }

            is LoginSideEffect.EmptyPasswordException, LoginSideEffect.MatchPasswordException -> {
                passwordFocusRequester.requestFocus(keyboardController = keyboardController)
                errorText = errorList[it]!!
            }

            is LoginSideEffect.UnKnownException -> {}
        }
    }

    IndiStrawColumnBackground(
        onClickAction = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    ) {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack(keyboardController = keyboardController) })
        HeadLineBold(
            text = stringResource(id = R.string.do_login),
            modifier = Modifier.padding(start = 32.dp, top = 16.dp)
        )
        IndiStrawTextField(
            modifier = Modifier
                .padding(top = 97.dp)
                .focusRequester(focusRequester = idFocusRequester),
            hint = stringResource(id = R.string.id),
            value = id,
            onValueChange = {
                if (errorText.isNotEmpty()) errorText = ""
                id = it
            },
            imeAction = ImeAction.Next
        )
        IndiStrawTextField(
            modifier = Modifier
                .padding(top = 20.dp)
                .focusRequester(focusRequester = passwordFocusRequester),
            hint = stringResource(id = R.string.password),
            value = password,
            onValueChange = {
                if (errorText.isNotEmpty()) errorText = ""
                password = it
            },
            isPasswordVisible = isPasswordVisible,
            tailingIcon = {
                IndiStrawIcon(
                    modifier = Modifier
                        .height(15.dp)
                        .indiStrawClickable(onClick = { isPasswordVisible = !isPasswordVisible }),
                    icon = IndiStrawIconList.OpenEyes
                )
            }
        )
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 7.dp),
            text = errorText,
            color = IndiStrawTheme.colors.red,
            fontSize = 12
        )
        IndiStrawButton(
            modifier = Modifier.padding(top = 37.dp),
            text = stringResource(id = R.string.login)
        ) {
            loginViewModel.login(id = id, password = password)
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ExampleTextRegular(
                modifier = Modifier
                    .indiStrawClickable(onClick = {
                        navController.navigate(
                            route = AuthNavigationItem.Certificate.route
                                + AuthDeepLinkKey.CERTIFICATE_TYPE + CertificateType.FIND_ID
                        )
                    }),
                text = stringResource(id = R.string.find_id),
                color = IndiStrawTheme.colors.gray,
                fontSize = 12
            )
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .height(14.dp)
                    .width(1.dp)
                    .background(IndiStrawTheme.colors.darkGray)
                    .align(CenterVertically)
            )
            ExampleTextRegular(
                modifier = Modifier
                    .indiStrawClickable(onClick = {
                        navController.navigate(
                            route = AuthNavigationItem.Certificate.route
                                + AuthDeepLinkKey.CERTIFICATE_TYPE + CertificateType.FIND_PASSWORD
                        )
                    }),
                text = stringResource(id = R.string.find_password),
                color = IndiStrawTheme.colors.gray,
                fontSize = 12
            )
        }
    }
}