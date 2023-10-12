package com.danbam.indistraw.feature.tv.setting

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import coil.compose.AsyncImage
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.DialogMedium
import com.danbam.indistraw.core.design_system.component.IndiStrawTvBackground
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.IndiStrawTvTitleDialog
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.util.androidx.observeWithLifecycle
import com.danbam.indistraw.feature.tv.navigation.main.MainNavigationItem
import kotlinx.coroutines.InternalCoroutinesApi

sealed class SettingNavigation(val stringId: Int) {
    companion object {
        fun toList() = listOf(Language, Term, Account, Logout, Withdrawal)
    }

    object Language : SettingNavigation(R.string.tv_setting_language)
    object Term : SettingNavigation(R.string.tv_setting_term)
    object Account : SettingNavigation(R.string.tv_setting_account)
    object Logout : SettingNavigation(R.string.tv_setting_logout)
    object Withdrawal : SettingNavigation(R.string.tv_setting_withdrawal)
}

@OptIn(ExperimentalTvMaterial3Api::class, InternalCoroutinesApi::class)
@Composable
fun SettingScreen(
    navController: NavController,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val container = settingViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var selectedSettingMenu: SettingNavigation? by remember { mutableStateOf(null) }
    var settingDialogVisible by remember { mutableStateOf(false) }
    var isLogout by remember { mutableStateOf(true) }
    val languageFocusRequester = remember { FocusRequester() }

    sideEffect.observeWithLifecycle {
        when (it) {
            is SettingSideEffect.SuccessLogout -> {
                settingDialogVisible = false
                navController.navigate(MainNavigationItem.Login.route)
            }

            is SettingSideEffect.SuccessWithdraw -> {
                settingDialogVisible = false
                navController.navigate(MainNavigationItem.Login.route)
            }
        }
    }

    LaunchedEffect(Unit) {
        settingViewModel.fetchLanguage()
        settingViewModel.profile()
    }

    BackHandler(selectedSettingMenu != null) {
        selectedSettingMenu = null
    }

    IndiStrawTvBackground {
        IndiStrawTvTitleDialog(
            visible = settingDialogVisible,
            title = if (isLogout) stringResource(id = R.string.logout) else stringResource(id = R.string.withdrawal),
            content = if (isLogout) stringResource(id = R.string.want_you_logout) else stringResource(
                id = R.string.want_you_withdrawal
            ),
            onDismissRequest = { settingDialogVisible = false },
            onOkay = { if (isLogout) settingViewModel.logout() else settingViewModel.withdraw() }
        )
        Row {
            Column(
                modifier = Modifier
                    .onFocusChanged {
                        if (it.hasFocus) {
                            selectedSettingMenu = null
                        }
                    }
                    .padding(start = 40.dp, top = 60.dp, bottom = 100.dp)
                    .fillMaxWidth(0.23F)
                    .fillMaxHeight()
            ) {
                DialogMedium(text = stringResource(id = R.string.setting), fontSize = 48)
                Spacer(modifier = Modifier.height(60.dp))
                TvLazyColumn {
                    items(SettingNavigation.toList()) {
                        Surface(
                            modifier = Modifier
                                .focusRequester(if (it == SettingNavigation.Language) languageFocusRequester else FocusRequester())
                                .padding(top = if (it == SettingNavigation.Logout) 50.dp else 20.dp)
                                .fillMaxWidth(),
                            scale = ClickableSurfaceDefaults.scale(
                                focusedScale = 1F
                            ),
                            color = ClickableSurfaceDefaults.color(
                                color = Color.Transparent,
                                focusedColor = Color.Transparent,
                                pressedColor = Color.Transparent,
                                disabledColor = Color.Transparent
                            ),
                            border = ClickableSurfaceDefaults.border(
                                focusedBorder = Border(
                                    border = BorderStroke(3.dp, IndiStrawTheme.colors.main),
                                    shape = IndiStrawTheme.shapes.smallRounded
                                )
                            ),
                            onClick = {
                                selectedSettingMenu = it
                            }
                        ) {
                            TitleRegular(
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 4.dp
                                ),
                                text = stringResource(id = it.stringId),
                                fontSize = 31,
                                color = IndiStrawTheme.colors.gray
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(25.dp))
            when (selectedSettingMenu) {
                is SettingNavigation.Language -> {
                    SettingLanguageScreen(
                        selectLanguage = state.currentLanguage,
                        parentFocusRequester = languageFocusRequester,
                        settingViewModel = settingViewModel
                    )
                }

                is SettingNavigation.Term -> {
                    SettingTermScreen()
                }

                is SettingNavigation.Account -> {
                    SettingAccountScreen()
                }

                is SettingNavigation.Logout -> {
                    isLogout = true
                    settingDialogVisible = true
                    selectedSettingMenu = null
                }

                is SettingNavigation.Withdrawal -> {
                    isLogout = false
                    settingDialogVisible = true
                    selectedSettingMenu = null
                }

                else -> {}
            }
            Spacer(modifier = Modifier.weight(1F))
            Column(
                modifier = Modifier
                    .width(180.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.profileEntity.profileUrl != null) {
                    AsyncImage(
                        modifier = Modifier
                            .size(180.dp)
                            .clip(IndiStrawTheme.shapes.circle),
                        model = state.profileEntity.profileUrl,
                        contentDescription = "profileThumbnail",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(180.dp)
                            .background(
                                color = IndiStrawTheme.colors.gray,
                                shape = IndiStrawTheme.shapes.circle
                            )
                    ) {
                        IndiStrawIcon(
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.Center),
                            icon = IndiStrawIconList.Profile,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                ExampleTextMedium(text = state.profileEntity.name, fontSize = 35)
            }
            Spacer(modifier = Modifier.width(50.dp))
        }
    }
}