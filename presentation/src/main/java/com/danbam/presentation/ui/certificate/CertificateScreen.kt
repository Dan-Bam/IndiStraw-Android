package com.danbam.presentation.ui.certificate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.FindPasswordMedium
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.presentation.R
import com.danbam.presentation.util.AppNavigationItem
import com.danbam.presentation.util.CertificateType
import com.danbam.presentation.util.SignUpNavigationItem
import com.danbam.presentation.util.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun CertificateScreen(
    navController: NavController,
    certificateViewModel: CertificateViewModel = hiltViewModel(),
    certificateType: String,
) {
    val container = certificateViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var phoneNumber by remember { mutableStateOf("") }
    var certificateNumber by remember { mutableStateOf("") }
    var onReTimer by remember { mutableStateOf({}) }

    sideEffect.observeWithLifecycle {
        when (it) {
            CertificateSideEffect.SuccessSend -> {}
            CertificateSideEffect.Certificated -> {
                when (certificateType) {
                    CertificateType.SIGN_UP -> navController.navigate(SignUpNavigationItem.SetProfile.route)
                    CertificateType.FIND_ID -> navController.navigate(AppNavigationItem.FindId.route)
                    CertificateType.FIND_PASSWORD -> navController.navigate(AppNavigationItem.FindPassword.route)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        IndiStrawHeader(
            marginTop = 25,
            backStringId = R.string.back,
            pressBackBtn = {
                navController.popBackStack()
            })
        HeadLineBold(
            modifier = Modifier
                .padding(start = 32.dp, top = 16.dp),
            text = stringResource(id = if (state.phoneNumber.isNotEmpty()) R.string.require_certificate_number else R.string.require_phone_number)
        )
        IndiStrawTextField(
            modifier = Modifier.padding(top = 96.dp),
            hint = stringResource(id = R.string.phone_number),
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            readOnly = state.phoneNumber.isNotEmpty(),
            keyboardType = KeyboardType.Number
        )
        if (state.phoneNumber.isNotEmpty()) {
            IndiStrawTextField(
                modifier = Modifier.padding(top = 20.dp),
                hint = stringResource(id = R.string.certificate_number),
                value = certificateNumber,
                onValueChange = { certificateNumber = it },
                keyboardType = KeyboardType.Number,
                isTimer = true,
                onReTimer = { onReTimer = it }
            )
        }
        IndiStrawButton(
            modifier = Modifier.padding(top = (if (state.phoneNumber.isNotEmpty()) 37 else 78).dp),
            text = stringResource(id = if (state.phoneNumber.isNotEmpty()) R.string.check_certificate_number else R.string.certificate_number)
        ) {
            if (state.phoneNumber.isEmpty()) {
                certificateViewModel.sendCertificateNumber(phoneNumber = phoneNumber)
            } else {
                certificateViewModel.checkCertificateNumber(authCode = certificateNumber)
            }
        }
        if (state.phoneNumber.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .indiStrawClickable(onClick = onReTimer),
                horizontalArrangement = Arrangement.Center
            ) {
                FindPasswordMedium(
                    text = stringResource(id = R.string.already_certificate_number),
                    color = IndiStrawTheme.colors.exampleText
                )
                Spacer(modifier = Modifier.width(2.dp))
                FindPasswordMedium(text = stringResource(id = R.string.re_send))
            }
        }
    }
}