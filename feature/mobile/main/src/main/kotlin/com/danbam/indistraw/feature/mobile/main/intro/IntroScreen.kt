package com.danbam.indistraw.feature.mobile.main.intro

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.IndiStrawBoxBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.JoinBold
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.util.android.Language
import com.danbam.indistraw.core.design_system.util.android.changeLanguage
import com.danbam.indistraw.core.design_system.util.androidx.observeWithLifecycle
import com.danbam.indistraw.feature.mobile.auth.navigation.AuthNavigationItem
import com.danbam.indistraw.feature.mobile.main.navigation.MainNavigationItem
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

    val context = LocalContext.current

    sideEffect.observeWithLifecycle {
        when (it) {
            is IntroSideEffect.SuccessLogin -> {
                delay(1_000L)
                navController.navigate(MainNavigationItem.Main.route)
            }
        }
    }

    LaunchedEffect(true) {
        introViewModel.isLogin()
        introViewModel.fetchLanguage()
    }

    LaunchedEffect(state.systemLanguage) {
        state.systemLanguage?.let { language ->
            Language.toList().forEach {
                if (it.type == language) it.changeLanguage(context)
            }
        }
    }

    IndiStrawBoxBackground {
        IndiStrawIcon(
            modifier = Modifier.align(Alignment.Center),
            icon = IndiStrawIconList.Logo
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
                    navController.navigate(com.danbam.indistraw.feature.mobile.auth.navigation.AuthNavigationItem.Login.route)
                }
                Spacer(modifier = Modifier.height(33.dp))
                Row(
                    modifier = Modifier
                        .indiStrawClickable(onClick = {
                            navController.navigate(com.danbam.indistraw.feature.mobile.auth.navigation.AuthNavigationItem.SetName.route)
                        })
                ) {
                    TitleRegular(
                        text = stringResource(id = R.string.already_sign_up),
                        color = IndiStrawTheme.colors.gray,
                        fontSize = 12
                    )
                    JoinBold(
                        text = stringResource(id = R.string.sign_up),
                        modifier = Modifier.padding(horizontal = 6.dp),
                        fontSize = 12
                    )
                    TitleRegular(
                        text = stringResource(id = R.string.go),
                        color = IndiStrawTheme.colors.gray,
                        fontSize = 12
                    )
                }
            }
        }
    }

}