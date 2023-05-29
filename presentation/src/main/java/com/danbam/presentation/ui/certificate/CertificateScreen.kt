package com.danbam.presentation.ui.certificate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.presentation.R
import com.danbam.presentation.util.AppNavigationItem
import com.danbam.presentation.util.CertificateType
import com.danbam.presentation.util.SignUpNavigationItem

@Composable
fun CertificateScreen(
    navController: NavController,
    certificateType: String,
) {
    var isSendCertificateNumber by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }
    var certificateNumber by remember { mutableStateOf("") }
    Column {
        IndiStrawHeader(
            marginTop = if (certificateType == CertificateType.SIGN_UP) 25 else 46,
            backStringId = R.string.back,
            pressBackBtn = {
                if (isSendCertificateNumber) {
                    isSendCertificateNumber = false
                } else {
                    navController.popBackStack()
                }
            })
        HeadLineBold(
            modifier = Modifier
                .padding(start = 32.dp, top = 16.dp),
            text = stringResource(id = if (isSendCertificateNumber) R.string.require_certificate_number else R.string.require_phone_number)
        )
        IndiStrawTextField(
            modifier = Modifier.padding(top = 96.dp),
            hint = stringResource(id = R.string.phone_number),
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            readOnly = isSendCertificateNumber,
            keyboardType = KeyboardType.Number
        )
        if (isSendCertificateNumber) {
            IndiStrawTextField(
                modifier = Modifier.padding(top = 20.dp),
                hint = stringResource(id = R.string.certificate_number),
                value = certificateNumber,
                onValueChange = { certificateNumber = it },
                keyboardType = KeyboardType.Number,
                isTimer = true
            )
        }
        IndiStrawButton(
            modifier = Modifier.padding(top = (if (isSendCertificateNumber) 37 else 78).dp),
            text = stringResource(id = if (isSendCertificateNumber) R.string.check_certificate_number else R.string.certificate_number)
        ) {
            if (!isSendCertificateNumber) {
                isSendCertificateNumber = true
            } else {
                when (certificateType) {
                    CertificateType.SIGN_UP -> navController.navigate(SignUpNavigationItem.SetProfile.route)
                    CertificateType.FIND_ID -> navController.navigate(AppNavigationItem.FindId.route)
                    CertificateType.FIND_PASSWORD -> navController.navigate(AppNavigationItem.FindPassword.route)
                }
            }
        }
    }
}