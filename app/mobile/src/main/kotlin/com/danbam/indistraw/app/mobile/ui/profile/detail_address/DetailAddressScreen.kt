package com.danbam.indistraw.app.mobile.ui.profile.detail_address

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.component.IndiStrawTextField
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.component.TitleSemiBold
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.app.mobile.ui.profile.navigation.ProfileNavigationItem
import com.danbam.indistraw.core.design_system.util.androidx.observeWithLifecycle
import com.danbam.indistraw.core.design_system.util.androidx.popBackStack
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class, ExperimentalComposeUiApi::class)
@Composable
fun DetailAddressScreen(
    navController: NavController,
    detailAddressViewModel: DetailAddressViewModel = hiltViewModel(),
    address: String,
    zipCode: String,
) {
    val container = detailAddressViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var detailAddress by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    val detailAddressFocusRequester = remember { FocusRequester() }

    val errorList = mapOf(
        DetailAddressSideEffect.EmptyDetailAddressException to stringResource(id = R.string.require_detail_address),
    )

    sideEffect.observeWithLifecycle {
        when (it) {
            DetailAddressSideEffect.EmptyDetailAddressException -> {
                detailAddressFocusRequester.requestFocus()
                errorText = errorList[it]!!
            }

            DetailAddressSideEffect.SuccessChangeAddress -> {
                focusManager.clearFocus()
                keyboardController?.hide()
                navController.popBackStack(ProfileNavigationItem.EditProfile.route, false)
            }
        }
    }

    IndiStrawColumnBackground(
        onClickAction = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    ) {
        IndiStrawHeader(pressBackBtn = { navController.popBackStack(keyboardController = keyboardController) })
        TitleSemiBold(
            modifier = Modifier.padding(start = 15.dp, top = 60.dp),
            text = stringResource(id = R.string.current_address),
            fontSize = 18
        )
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 13.dp),
            text = "$zipCode\n$address",
            fontSize = 14
        )
        Spacer(modifier = Modifier.height(42.dp))
        IndiStrawTextField(
            modifier = Modifier.focusRequester(focusRequester = detailAddressFocusRequester),
            hint = stringResource(id = R.string.detail_address),
            value = detailAddress,
            onValueChange = {
                if (errorText.isNotEmpty()) errorText = ""
                detailAddress = it
            })
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, top = 7.dp),
            text = errorText,
            color = IndiStrawTheme.colors.red,
            fontSize = 12
        )
        Spacer(modifier = Modifier.height(37.dp))
        IndiStrawButton(text = stringResource(id = R.string.check)) {
            detailAddressViewModel.changeAddress(detailAddress, address, zipCode)
        }
    }
}