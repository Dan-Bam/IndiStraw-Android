package com.danbam.tv.ui.main.main

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.tv.util.android.findActivity

@Composable
fun MainScreen() {
    val context = LocalContext.current
    BackHandler {
        context.findActivity()?.finish()
    }
    IndiStrawTvBackground {
        
    }
}