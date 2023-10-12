package com.danbam.indistraw.feature.mobile.profile.edit_profile

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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.component.FindPasswordMedium
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.component.IndiStrawTextField
import com.danbam.indistraw.core.design_system.component.SelectProfileButton
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.util.android.toFile
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.feature.mobile.auth.navigation.AuthDeepLinkKey
import com.danbam.indistraw.feature.mobile.auth.navigation.AuthNavigationItem
import com.danbam.indistraw.feature.mobile.auth.navigation.CertificateType
import com.danbam.indistraw.feature.mobile.profile.navigation.ProfileNavigationItem
import com.danbam.indistraw.core.design_system.util.androidx.observeWithLifecycle
import com.danbam.indistraw.core.design_system.util.androidx.popBackStack
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
    var errorText by remember { mutableStateOf("") }
    val nameFocusRequester = remember { FocusRequester() }

    val errorList = mapOf(
        EditProfileSideEffect.EmptyNameException to stringResource(id = R.string.require_name),
    )

    sideEffect.observeWithLifecycle {
        when (it) {
            is EditProfileSideEffect.GetProfile -> {
                name = it.name
            }

            is EditProfileSideEffect.SuccessUpload -> {
            }

            is EditProfileSideEffect.EmptyNameException -> {
                nameFocusRequester.requestFocus()
                errorText = errorList[it]!!
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
        SelectProfileButton(
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
                modifier = Modifier.focusRequester(focusRequester = nameFocusRequester),
                hint = stringResource(id = R.string.name),
                value = name,
                onValueChange = {
                    if (errorText.isNotEmpty()) errorText = ""
                    name = it
                })
            Spacer(modifier = Modifier.height(20.dp))
            IndiStrawTextField(
                hint = stringResource(id = R.string.phone_number),
                value = state.phoneNumber,
                readOnly = true,
                tailingIcon = {
                    FindPasswordMedium(
                        modifier = Modifier.indiStrawClickable {
                            navController.navigate(
                                com.danbam.indistraw.feature.mobile.auth.navigation.AuthNavigationItem.Certificate.route + com.danbam.indistraw.feature.mobile.auth.navigation.AuthDeepLinkKey.CERTIFICATE_TYPE + com.danbam.indistraw.feature.mobile.auth.navigation.CertificateType.CHANGE_PHONE_NUMBER
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
            TitleRegular(
                modifier = Modifier.padding(start = 15.dp, top = 7.dp),
                text = errorText,
                color = IndiStrawTheme.colors.red,
                fontSize = 12
            )
            IndiStrawButton(
                modifier = Modifier.padding(top = 63.dp),
                text = stringResource(id = R.string.save)
            ) {
                editProfileVieModel.saveProfile(name)
            }
        }
    }
}