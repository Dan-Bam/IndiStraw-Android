package com.danbam.tv.ui.setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.component.DialogMedium
import com.danbam.design_system.component.TitleRegular

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingLanguageScreen(

) {
    Column(
        modifier = Modifier
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
}