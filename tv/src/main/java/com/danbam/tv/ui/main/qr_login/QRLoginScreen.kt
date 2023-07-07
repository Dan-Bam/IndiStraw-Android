package com.danbam.tv.ui.main.qr_login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.danbam.tv.BuildConfig
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.R
import com.danbam.design_system.component.QRPainter
import kotlinx.coroutines.delay

const val QR_LOGIN_TIME = 300

@Composable
fun QRLoginScreen(
    navController: NavController,
    qrLoginViewModel: QRLoginViewModel = hiltViewModel()
) {
    val container = qrLoginViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var restTime by remember { mutableStateOf(QR_LOGIN_TIME) }

    LaunchedEffect(Unit) {
        qrLoginViewModel.getQRCode()
    }

    LaunchedEffect(restTime) {
        if (restTime != 0) {
            delay(1_000L)
            restTime--
        }
    }

    IndiStrawTvBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TitleRegular(
                text = stringResource(id = R.string.remain_qr_time),
                fontSize = 24,
                color = IndiStrawTheme.colors.gray
            )
            ExampleTextMedium(
                text = "${restTime / 60}:${"%02d".format(restTime % 60)}",
                fontSize = 48,
                color = IndiStrawTheme.colors.gray
            )
            Box {
                IndiStrawIcon(
                    modifier = Modifier.fillMaxSize(0.7F),
                    icon = IndiStrawIconList.QRGrid
                )
                state.uuid?.let {
                    QRPainter(
                        modifier = Modifier
                            .align(Alignment.Center),
                        url = "${BuildConfig.QR_URL}$it"
                    )
                }
            }
        }
    }
}