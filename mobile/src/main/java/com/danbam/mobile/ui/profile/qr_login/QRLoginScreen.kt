package com.danbam.mobile.ui.profile.qr_login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.QRScanner
import com.danbam.design_system.R
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.TitleRegular
import com.danbam.mobile.BuildConfig
import java.util.UUID

@Composable
fun QRLoginScreen(
    navController: NavController,
) {
    var uuid: UUID? by remember { mutableStateOf(null) }
    IndiStrawColumnBackground {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
        HeadLineBold(
            modifier = Modifier.padding(start = 35.dp, top = 16.dp),
            text = stringResource(id = R.string.qr_login)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IndiStrawIcon(
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.Center),
                icon = IndiStrawIconList.QRGrid
            )
            QRScanner(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(265.dp)
            ) {
                it?.let {
                    if (it.startsWith(BuildConfig.QR_URL)) {
                        uuid = UUID.fromString(it.split("/").last())
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(41.dp))
        TitleRegular(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.require_qr),
            textAlign = TextAlign.Center
        )
        uuid?.let {
            Spacer(modifier = Modifier.height(112.dp))
            IndiStrawButton(text = stringResource(id = R.string.check)) {
            }
        }
    }
}