package com.danbam.indistraw.tv.ui.setting

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.util.android.Language
import com.danbam.indistraw.core.design_system.util.android.changeLanguage

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingLanguageScreen(
    selectLanguage: Language,
    parentFocusRequester: FocusRequester,
    settingViewModel: SettingViewModel
) {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BackHandler {
        parentFocusRequester.requestFocus()
    }

    Row {
        Column(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .fillMaxWidth(0.4F)
                .fillMaxHeight()
        ) {
            TvLazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(Language.toList()) {
                    Surface(
                        modifier = Modifier.focusRequester(if (selectLanguage == it) focusRequester else FocusRequester()),
                        scale = ClickableSurfaceDefaults.scale(
                            focusedScale = 1F
                        ),
                        shape = ClickableSurfaceDefaults.shape(
                            shape = RoundedCornerShape(0.dp)
                        ),
                        color = ClickableSurfaceDefaults.color(
                            color = Color.Transparent,
                            focusedColor = IndiStrawTheme.colors.main,
                            pressedColor = IndiStrawTheme.colors.main,
                            disabledColor = Color.Transparent
                        ),
                        onClick = {
                            settingViewModel.saveLanguage(it)
                            it.changeLanguage(context)
                        }
                    ) {
                        ExampleTextMedium(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            text = stringResource(id = it.stringId), fontSize = 31,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .width(3.dp)
                .fillMaxHeight()
                .background(IndiStrawTheme.colors.gray)
        )
    }
}