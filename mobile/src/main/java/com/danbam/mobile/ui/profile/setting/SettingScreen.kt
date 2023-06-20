package com.danbam.mobile.ui.profile.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.mobile.ui.auth.navigation.AuthDeepLinkKey
import com.danbam.mobile.ui.auth.navigation.AuthNavigationItem
import com.danbam.mobile.ui.auth.navigation.CertificateType
import com.danbam.mobile.ui.main.navigation.MainNavigationItem
import com.danbam.mobile.ui.profile.navigation.ProfileNavigationItem
import com.danbam.mobile.util.android.observeWithLifecycle
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

    sideEffect.observeWithLifecycle {
        when (it) {
            is SettingSideEffect.SuccessLogout -> {
                navController.navigate(AuthNavigationItem.Login.route) {
                    popUpTo(MainNavigationItem.Intro.route)
                }
            }

            is SettingSideEffect.SuccessWithdraw -> {
                navController.navigate(AuthNavigationItem.Login.route) {
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
        stringResource(id = R.string.change_language) to { changeLanguage() }
    )
    val thirdLine = mapOf(
        stringResource(id = R.string.logout) to {
            settingViewModel.logout()
        },
        stringResource(id = R.string.withdrawal) to {
            settingViewModel.withdraw()
        }
    )

    IndiStrawBottomSheetLayout(sheetContent = {

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
                modifier = Modifier.padding(start = 32.dp),
                text = stringResource(id = R.string.setting_account)
            )
            Spacer(modifier = Modifier.height(10.dp))
            SettingItem(
                itemMap = secondLine,
                frontIcon = listOf(IndiStrawIconList.Shield, IndiStrawIconList.Earth)
            )
            Spacer(modifier = Modifier.height(36.dp))
            SettingItem(itemMap = thirdLine)
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