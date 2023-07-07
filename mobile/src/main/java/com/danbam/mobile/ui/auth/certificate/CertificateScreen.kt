package com.danbam.mobile.ui.auth.certificate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.FindPasswordMedium
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.design_system.R
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.mobile.ui.auth.navigation.AuthDeepLinkKey
import com.danbam.mobile.ui.auth.navigation.AuthNavigationItem
import com.danbam.mobile.ui.auth.navigation.CertificateType
import com.danbam.mobile.util.android.observeWithLifecycle
import com.danbam.mobile.util.view.popBackStack
import com.danbam.mobile.util.view.requestFocus
import com.danbam.mobile.util.parser.toPhoneNumber
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

const val RestTime = 300

@OptIn(InternalCoroutinesApi::class, ExperimentalComposeUiApi::class)
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
    var errorText by remember { mutableStateOf("") }
    var restTime by remember { mutableStateOf(RestTime) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val phoneNumberFocusRequester = remember { FocusRequester() }
    val certificateNumberFocusRequester = remember { FocusRequester() }

    val errorList = mapOf(
        CertificateSideEffect.EmptyPhoneNumberException to stringResource(id = R.string.require_phone_number),
        CertificateSideEffect.MatchPhoneNumberException to stringResource(id = R.string.wrong_match_phone_number),
        CertificateSideEffect.EnrollPhoneNumberException to stringResource(id = R.string.wrong_enroll_phone_number),
        CertificateSideEffect.NotEnrollPhoneNumberException to stringResource(id = R.string.wrong_not_enroll_phone_number),
        CertificateSideEffect.TooManyRequestPhoneNumberException to stringResource(id = R.string.wrong_over_request_phone_number),
        CertificateSideEffect.EmptyCertificateNumberException to stringResource(id = R.string.require_certificate_number),
        CertificateSideEffect.WrongCertificateNumberException to stringResource(id = R.string.wrong_certificate_number),
        CertificateSideEffect.ExpiredCertificateNumberException to stringResource(id = R.string.wrong_expired_certificate_number),
        CertificateSideEffect.TooManyRequestCertificateNumberException to stringResource(id = R.string.wrong_over_request_certificate_number)
    )

    LaunchedEffect(Unit) {
        certificateViewModel.clearPhoneNumber()
    }

    LaunchedEffect(restTime) {
        if (restTime != 0) {
            delay(1_000L)
            restTime--
        } else {
            certificateViewModel.expiredCertificateNumber()
        }
    }

    sideEffect.observeWithLifecycle {
        when (it) {
            is CertificateSideEffect.EmptyPhoneNumberException, CertificateSideEffect.MatchPhoneNumberException, CertificateSideEffect.EnrollPhoneNumberException, CertificateSideEffect.NotEnrollPhoneNumberException, CertificateSideEffect.TooManyRequestPhoneNumberException -> {
                phoneNumberFocusRequester.requestFocus(keyboardController = keyboardController)
                errorText = errorList[it]!!
            }

            is CertificateSideEffect.EmptyCertificateNumberException, CertificateSideEffect.WrongCertificateNumberException, CertificateSideEffect.ExpiredCertificateNumberException, CertificateSideEffect.TooManyRequestCertificateNumberException -> {
                certificateNumberFocusRequester.requestFocus(keyboardController = keyboardController)
                errorText = errorList[it]!!
            }

            is CertificateSideEffect.SuccessChangePhoneNumber -> {
                navController.popBackStack(keyboardController = keyboardController)
            }

            is CertificateSideEffect.SuccessCertificate -> {
                keyboardController?.hide()
                when (certificateType) {
                    CertificateType.SIGN_UP -> navController.navigate(AuthNavigationItem.SetProfile.route + AuthDeepLinkKey.PHONE_NUMBER + phoneNumber.toPhoneNumber())
                    CertificateType.FIND_ID -> navController.navigate(AuthNavigationItem.FindId.route + AuthDeepLinkKey.PHONE_NUMBER + phoneNumber.toPhoneNumber())
                    CertificateType.FIND_PASSWORD -> navController.navigate(AuthNavigationItem.FindPassword.route + AuthDeepLinkKey.PHONE_NUMBER + phoneNumber.toPhoneNumber() + AuthDeepLinkKey.IS_FIND_PASSWORD + true)
                    CertificateType.CHANGE_PASSWORD -> navController.navigate(AuthNavigationItem.FindPassword.route + AuthDeepLinkKey.PHONE_NUMBER + phoneNumber.toPhoneNumber() + AuthDeepLinkKey.IS_FIND_PASSWORD + false)
                    CertificateType.CHANGE_PHONE_NUMBER -> certificateViewModel.changePhoneNumber(
                        phoneNumber = phoneNumber
                    )
                }
            }
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
            modifier = Modifier
                .padding(start = 32.dp, top = 16.dp),
            text = stringResource(id = if (state.phoneNumber.isNotEmpty()) R.string.require_certificate_number else if (certificateType != CertificateType.CHANGE_PHONE_NUMBER) R.string.require_phone_number else R.string.require_new_phone_number)
        )
        IndiStrawTextField(
            modifier = Modifier
                .padding(top = 96.dp)
                .focusRequester(focusRequester = phoneNumberFocusRequester),
            hint = stringResource(id = R.string.phone_number),
            value = phoneNumber,
            onValueChange = {
                if (errorText.isNotEmpty()) errorText = ""
                phoneNumber = it
            },
            readOnly = state.phoneNumber.isNotEmpty(),
            keyboardType = KeyboardType.Number
        )
        if (state.phoneNumber.isNotEmpty()) {
            IndiStrawTextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .focusRequester(focusRequester = certificateNumberFocusRequester),
                hint = stringResource(id = R.string.certificate_number),
                value = certificateNumber,
                onValueChange = {
                    if (errorText.isNotEmpty()) errorText = ""
                    certificateNumber = it
                },
                keyboardType = KeyboardType.Number,
                tailingIcon = {
                    ExampleTextMedium(
                        text = "(${restTime / 60}:${"%02d".format(restTime % 60)})",
                    )
                }
            )
        }
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 7.dp),
            text = errorText,
            color = IndiStrawTheme.colors.red,
            fontSize = 12
        )
        IndiStrawButton(
            modifier = Modifier.padding(top = (if (state.phoneNumber.isNotEmpty()) 37 else 78).dp),
            text = stringResource(id = if (state.phoneNumber.isNotEmpty()) R.string.check_certificate_number else R.string.certificate_number)
        ) {
            if (state.phoneNumber.isEmpty()) {
                certificateViewModel.checkPhoneNumber(
                    phoneNumber = phoneNumber.toPhoneNumber(),
                    type = if (certificateType == CertificateType.SIGN_UP) "SIGNUP" else if (certificateType == CertificateType.CHANGE_PHONE_NUMBER) "CHANGE_PHONE" else "FIND_ACCOUNT"
                )
            } else {
                certificateViewModel.checkCertificateNumber(authCode = certificateNumber)
            }
        }
        if (state.phoneNumber.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .indiStrawClickable(onClick = {
                        certificateViewModel.sendCertificateNumber(phoneNumber = phoneNumber.toPhoneNumber())
                        restTime = RestTime
                    }),
                horizontalArrangement = Arrangement.Center
            ) {
                FindPasswordMedium(
                    text = stringResource(id = R.string.already_certificate_number),
                    color = IndiStrawTheme.colors.gray
                )
                Spacer(modifier = Modifier.width(2.dp))
                FindPasswordMedium(text = stringResource(id = R.string.re_send))
            }
        }
    }
}