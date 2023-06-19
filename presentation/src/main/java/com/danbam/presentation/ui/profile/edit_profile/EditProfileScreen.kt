package com.danbam.presentation.ui.profile.edit_profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.component.FindPasswordMedium
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.component.SelectImageButton
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.presentation.ui.auth.navigation.AuthDeepLinkKey
import com.danbam.presentation.ui.auth.navigation.AuthNavigationItem
import com.danbam.presentation.ui.auth.navigation.CertificateType
import com.danbam.presentation.ui.profile.navigation.ProfileNavigationItem
import com.danbam.presentation.util.android.observeWithLifecycle
import com.danbam.presentation.util.parser.toFile
import com.danbam.presentation.util.view.popBackStack
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalComposeUiApi::class, InternalCoroutinesApi::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    editProfileVieModel: EditProfileVieModel = hiltViewModel(),
) {
    val container = editProfileVieModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    var name by remember { mutableStateOf(state.name) }
    val nameFocusRequester = remember { FocusRequester() }

    sideEffect.observeWithLifecycle {
        when (it) {
            is EditProfileSideEffect.GetProfile -> {
                name = it.name
            }

            is EditProfileSideEffect.SuccessUpload -> {
            }
        }
    }

    LaunchedEffect(Unit) {
        editProfileVieModel.getProfile()
    }

    IndiStrawColumnBackground(
        onClickAction = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    ) {
        IndiStrawHeader(pressBackBtn = { navController.popBackStack(keyboardController = keyboardController) })
        SelectImageButton(
            modifier = Modifier
                .padding(top = 22.dp)
                .align(Alignment.CenterHorizontally),
            paddingValues = PaddingValues(22.dp),
            imageUrl = state.profileUrl,
            selectGallery = {
                it?.let { editProfileVieModel.setProfileImage(it.toFile(context)) }
            },
            selectCamera = {
                it?.let { editProfileVieModel.setProfileImage(it.toFile(context)) }
            }) {
            Spacer(modifier = Modifier.height(64.dp))
            IndiStrawTextField(
                hint = stringResource(id = R.string.name),
                value = name,
                onValueChange = { name = it })
            Spacer(modifier = Modifier.height(20.dp))
            IndiStrawTextField(
                hint = stringResource(id = R.string.phone_number),
                value = state.phoneNumber,
                readOnly = true,
                tailingIcon = {
                    FindPasswordMedium(
                        modifier = Modifier.indiStrawClickable {
                            navController.navigate(
                                AuthNavigationItem.Certificate.route + AuthDeepLinkKey.CERTIFICATE_TYPE + CertificateType.CHANGE_PHONE_NUMBER
                            )
                        },
                        text = stringResource(id = R.string.change),
                        color = IndiStrawTheme.colors.skyBlue
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            IndiStrawTextField(
                hint = stringResource(id = R.string.address),
                value = state.address ?: "",
                readOnly = true,
                tailingIcon = {
                    FindPasswordMedium(
                        modifier = Modifier.indiStrawClickable {
                            navController.navigate(
                                ProfileNavigationItem.FindAddress.route
                            )
                        },
                        text = stringResource(id = R.string.find_address),
                        color = IndiStrawTheme.colors.skyBlue
                    )
                }
            )
            IndiStrawButton(
                modifier = Modifier.padding(top = 70.dp),
                text = stringResource(id = R.string.save)
            ) {
                editProfileVieModel.saveProfile()
            }
        }
    }
}