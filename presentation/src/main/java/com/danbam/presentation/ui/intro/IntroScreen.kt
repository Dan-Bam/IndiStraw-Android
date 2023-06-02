package com.danbam.presentation.ui.intro

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.JoinBold
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.presentation.R
import com.danbam.presentation.util.AppNavigationItem
import com.danbam.presentation.util.SignUpNavigationItem
import com.danbam.presentation.util.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

@OptIn(InternalCoroutinesApi::class)
@Composable
fun IntroScreen(
    navController: NavController,
    introViewModel: IntroViewModel = hiltViewModel(),
) {
    val container = introViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    sideEffect.observeWithLifecycle {
        when (it) {
            is IntroSideEffect.SuccessLogin -> {
                delay(1_000L)
                navController.navigate(AppNavigationItem.Main.route)
            }
        }
    }

    LaunchedEffect(true) {
        introViewModel.isLogin()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HeadLineBold(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.app_name),
            fontSize = 30
        )
        AnimatedVisibility(
            visible = state.isNeedLogin,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 39.dp),
            enter = fadeIn(animationSpec = tween(durationMillis = 1500, delayMillis = 300))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IndiStrawButton(
                    text = stringResource(id = R.string.login)
                ) {
                    navController.navigate(AppNavigationItem.Login.route)
                }
                Spacer(modifier = Modifier.height(33.dp))
                Row(
                    modifier = Modifier
                        .indiStrawClickable(onClick = {
                            navController.navigate(SignUpNavigationItem.SetName.route)
                        })
                ) {
                    TitleRegular(
                        text = stringResource(id = R.string.already_sign_up),
                        color = IndiStrawTheme.colors.exampleText,
                        fontSize = 12
                    )
                    JoinBold(
                        text = stringResource(id = R.string.sign_up),
                        modifier = Modifier.padding(horizontal = 6.dp),
                        fontSize = 12
                    )
                    TitleRegular(
                        text = stringResource(id = R.string.go),
                        color = IndiStrawTheme.colors.exampleText,
                        fontSize = 12
                    )
                }
            }
        }
    }
}