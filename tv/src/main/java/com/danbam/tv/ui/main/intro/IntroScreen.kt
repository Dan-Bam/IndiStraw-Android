package com.danbam.tv.ui.main.intro

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.util.Language
import com.danbam.design_system.util.changeLanguage
import com.danbam.tv.ui.main.navigation.MainNavigationItem
import com.danbam.tv.util.android.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun IntroScreen(
    navController: NavController,
    introViewModel: IntroViewModel = hiltViewModel()
) {
    val container = introViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    LaunchedEffect(Unit) {
        introViewModel.fetchLanguage()
    }

    val context = LocalContext.current

    LaunchedEffect(state.currentLanguage) {
        state.currentLanguage?.let { language ->
            Language.toList().forEach {
                if (it.type == language) it.changeLanguage(context)
            }
        }
    }

    sideEffect.observeWithLifecycle {
        when (it) {
            is IntroSideEffect.LoginSuccess -> {
                navController.navigate(MainNavigationItem.Main.route)
            }

            is IntroSideEffect.LoginFail -> {
                navController.navigate(MainNavigationItem.Login.route)
            }
        }
    }

    IndiStrawTvBackground {

    }
}