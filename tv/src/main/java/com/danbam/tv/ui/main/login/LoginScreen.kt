package com.danbam.tv.ui.main.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.R
import com.danbam.design_system.component.ExampleTextRegular
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.component.IndiStrawTvButton
import com.danbam.design_system.component.IndiStrawTvDialog
import com.danbam.design_system.component.IndiStrawTvTextField
import com.danbam.tv.ui.main.navigation.MainNavigationItem
import com.danbam.tv.util.android.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val container = loginViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var errorDialogVisible by remember { mutableStateOf(false) }

    val errorList = mapOf(
        LoginSideEffect.EmptyIdException to stringResource(id = R.string.require_id),
        LoginSideEffect.EmptyPasswordException to stringResource(id = R.string.require_password),
        LoginSideEffect.MatchIdException to stringResource(id = R.string.wrong_id),
        LoginSideEffect.MatchPasswordException to stringResource(id = R.string.wrong_password)
    )

    sideEffect.observeWithLifecycle {
        when (it) {
            is LoginSideEffect.LoginSuccess -> {
                navController.navigate(MainNavigationItem.Main.route)
            }

            is LoginSideEffect.EmptyIdException, LoginSideEffect.MatchIdException -> {
                errorDialogVisible = true
                errorText = errorList[it]!!
            }

            is LoginSideEffect.EmptyPasswordException, LoginSideEffect.MatchPasswordException -> {
                errorDialogVisible = true
                errorText = errorList[it]!!
            }

            is LoginSideEffect.UnKnownException -> {}
        }
    }

    LaunchedEffect(Unit) {
        loginViewModel.isLogin()
    }

    IndiStrawTvBackground {
        IndiStrawTvDialog(
            visible = errorDialogVisible,
            content = errorText,
            onDismissRequest = { errorDialogVisible = false })
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1F))
            HeadLineBold(text = stringResource(id = R.string.app_name), fontSize = 57)
            Spacer(modifier = Modifier.fillMaxHeight(0.05F))
            IndiStrawTvTextField(
                hint = stringResource(id = R.string.id),
                value = id,
                onValueChange = { id = it })
            Spacer(modifier = Modifier.fillMaxHeight(0.04F))
            IndiStrawTvTextField(
                hint = stringResource(id = R.string.password),
                value = password,
                onValueChange = { password = it })

            Spacer(modifier = Modifier.fillMaxHeight(0.09F))
            IndiStrawTvButton(text = stringResource(id = R.string.login)) {
                loginViewModel.login(id = id, password = password)
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.02F))
            ExampleTextRegular(text = "OR", fontSize = 24)
            Spacer(modifier = Modifier.fillMaxHeight(0.02F))
            IndiStrawTvButton(text = stringResource(id = R.string.qr_login)) {
                navController.navigate(MainNavigationItem.QRLogin.route)
            }
        }
    }
}