package com.danbam.presentation.ui.profile.detail_address

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.R
import com.danbam.presentation.ui.profile.navigation.ProfileNavigationItem
import com.danbam.presentation.util.android.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
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

    var detailAddress by remember { mutableStateOf("") }

    sideEffect.observeWithLifecycle {
        when (it) {
            DetailAddressSideEffect.SuccessChangeAddress -> {
                navController.popBackStack(ProfileNavigationItem.EditProfile.route, false)
            }
        }
    }

    IndiStrawColumnBackground {
        IndiStrawHeader(pressBackBtn = { navController.popBackStack() })
        TitleSemiBold(
            modifier = Modifier.padding(start = 32.dp, top = 60.dp),
            text = stringResource(id = R.string.current_address),
            fontSize = 18
        )
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 13.dp),
            text = "$zipCode\n$address",
            fontSize = 14
        )
        Spacer(modifier = Modifier.height(42.dp))
        IndiStrawTextField(
            hint = stringResource(id = R.string.detail_address),
            value = detailAddress,
            onValueChange = { detailAddress = it })

        Spacer(modifier = Modifier.height(44.dp))
        IndiStrawButton(text = stringResource(id = R.string.check)) {
            detailAddressViewModel.changeAddress(detailAddress, address, zipCode)
        }
    }
}