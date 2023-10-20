package com.danbam.indistraw.feature.mobile.auth.signup

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.core.design_system.component.HeadLineBold
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.component.SelectProfileButton
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.util.android.toFile
import com.danbam.indistraw.core.design_system.util.androidx.getActivity
import com.danbam.indistraw.feature.mobile.navigation.auth.AuthNavigationItem

@Composable
fun SetProfileScreen(
    navController: NavController,
    phoneNumber: String,
    signUpViewModel: SignUpViewModel = hiltViewModel(getActivity()),
) {
    val container = signUpViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val context = LocalContext.current
    var profileUrl: String? by remember { mutableStateOf(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        signUpViewModel.setPhoneNumber(phoneNumber = phoneNumber)
    }

    IndiStrawColumnBackground(
        isLoading = isLoading
    ) {
        IndiStrawHeader(pressBackBtn = {
            navController.popBackStack()
        })
        HeadLineBold(
            modifier = Modifier
                .padding(start = 32.dp, top = 16.dp),
            text = stringResource(id = R.string.require_picture)
        )
        SelectProfileButton(
            modifier = Modifier
                .padding(top = 84.dp)
                .align(CenterHorizontally),
            paddingValues = PaddingValues(36.dp),
            isSignUp = true,
            imageUrl = profileUrl,
            selectGallery = {
                it?.let {
                    isLoading = true
                    signUpViewModel.setProfile(it.toFile(context)) {
                        isLoading = false
                        profileUrl = it
                    }
                }
            },
            selectCamera = {
                it?.let {
                    isLoading = true
                    signUpViewModel.setProfile(it.toFile(context)) {
                        isLoading = false
                        profileUrl = it
                    }
                }
            }) {
            IndiStrawButton(
                modifier = Modifier.padding(top = 156.dp),
                text = stringResource(id = R.string.next)
            ) {
                navController.navigate(AuthNavigationItem.SetId.route)
            }
        }
    }
}