package com.danbam.indistraw.feature.mobile.profile.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.ButtonMedium
import com.danbam.indistraw.core.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.indistraw.core.design_system.component.IndiStrawDialog
import com.danbam.indistraw.core.design_system.util.android.Language
import com.danbam.indistraw.core.design_system.util.androidx.RemoveOverScrollLazyColumn
import com.danbam.indistraw.core.design_system.util.android.changeLanguage
import com.danbam.indistraw.core.design_system.util.androidx.observeWithLifecycle
import com.danbam.indistraw.feature.mobile.navigation.auth.AuthDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.auth.AuthNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.auth.CertificateType
import com.danbam.indistraw.feature.mobile.navigation.main.MainNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.profile.ProfileNavigationItem
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalMaterialApi::class, InternalCoroutinesApi::class)
@Composable
fun SettingScreen(
    navController: NavController,
    settingViewModel: SettingViewModel = hiltViewModel(),
) {
    val container = settingViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val context = LocalContext.current
    var logoutDialogVisible by remember { mutableStateOf(false) }
    var withdrawDialogVisible by remember { mutableStateOf(false) }

    sideEffect.observeWithLifecycle {
        when (it) {
            is SettingSideEffect.SuccessLogout -> {
                navController.navigate(AuthNavigationItem.Login.route) {
                    logoutDialogVisible = false
                    popUpTo(MainNavigationItem.Intro.route)
                }
            }

            is SettingSideEffect.SuccessWithdraw -> {
                navController.navigate(AuthNavigationItem.Login.route) {
                    withdrawDialogVisible = false
                    popUpTo(MainNavigationItem.Intro.route)
                }
            }
        }
    }

    var changeLanguage: () -> Unit by remember { mutableStateOf({}) }

    val firstLine = mapOf(
        stringResource(id = R.string.change_profile) to {
            navController.navigate(ProfileNavigationItem.EditProfile.route)
        }
    )
    val secondLine = mapOf(
        stringResource(id = R.string.change_password) to {
            navController.navigate(AuthNavigationItem.Certificate.route + AuthDeepLinkKey.CERTIFICATE_TYPE + CertificateType.CHANGE_PASSWORD)
        },
        stringResource(id = R.string.change_language) to {
            changeLanguage()
        },
        stringResource(id = R.string.qr_login) to {
            navController.navigate(ProfileNavigationItem.QRLogin.route)
        }
    )
    val thirdLine = mapOf(
        stringResource(id = R.string.logout) to {
            logoutDialogVisible = true
        },
        stringResource(id = R.string.withdrawal) to {
            withdrawDialogVisible = true
        }
    )

    IndiStrawBottomSheetLayout(sheetContent = {
        RemoveOverScrollLazyColumn(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            items(Language.toList()) {
                ButtonMedium(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .indiStrawClickable {
                            settingViewModel.saveLanguage(it.type)
                            it.changeLanguage(context)
                        },
                    text = stringResource(id = it.stringId)
                )
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(IndiStrawTheme.colors.gray3)
                )
            }
        }
    }) { _, openBottomSheet ->
        changeLanguage = openBottomSheet
        IndiStrawColumnBackground {
            IndiStrawHeader(pressBackBtn = {
                navController.popBackStack()
            })
            Spacer(modifier = Modifier.height(60.dp))
            SettingItem(itemMap = firstLine, frontIcon = listOf(IndiStrawIconList.Profile))
            Spacer(modifier = Modifier.height(36.dp))
            ExampleTextMedium(
                modifier = Modifier.padding(start = 15.dp),
                text = stringResource(id = R.string.setting_account)
            )
            Spacer(modifier = Modifier.height(10.dp))
            SettingItem(
                itemMap = secondLine,
                frontIcon = listOf(
                    IndiStrawIconList.Shield,
                    IndiStrawIconList.Earth,
                    IndiStrawIconList.QR
                )
            )
            Spacer(modifier = Modifier.height(36.dp))
            SettingItem(itemMap = thirdLine)

            IndiStrawDialog(
                visible = logoutDialogVisible,
                title = stringResource(id = R.string.logout),
                content = stringResource(id = R.string.want_you_logout),
                onDismissRequest = { logoutDialogVisible = false }) {
                settingViewModel.logout()
            }

            IndiStrawDialog(
                visible = withdrawDialogVisible,
                title = stringResource(id = R.string.withdrawal),
                content = stringResource(id = R.string.want_you_withdrawal),
                onDismissRequest = { withdrawDialogVisible = false }) {
                settingViewModel.withdraw()
            }
        }

    }
}

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    itemMap: Map<String, () -> Unit>,
    frontIcon: List<IndiStrawIconList>? = null,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .background(
                color = IndiStrawTheme.colors.lightBlack,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .padding(vertical = 18.dp)
    ) {
        val itemKeys = itemMap.keys.toList()
        repeat(itemMap.size) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 13.dp)
                    .indiStrawClickable(onClick = itemMap[itemKeys[it]]),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (frontIcon != null) {
                    IndiStrawIcon(icon = frontIcon[it])
                    Spacer(modifier = Modifier.width(11.dp))
                }
                ExampleTextMedium(text = itemKeys[it])
            }
            if (it in (0 until itemMap.size - 1)) {
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(IndiStrawTheme.colors.darkGray3)
                )
            }
        }
    }
}