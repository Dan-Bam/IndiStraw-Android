package com.danbam.indistraw.feature.tv.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.HeadLineBold
import com.danbam.indistraw.core.design_system.R

@Composable
fun SettingAccountScreen(

) {
    Row {
        Column(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .fillMaxWidth(0.4F)
                .fillMaxHeight()
        ) {
            HeadLineBold(text = stringResource(id = R.string.account_info))
        }
    }
    Box(
        modifier = Modifier
            .width(3.dp)
            .fillMaxHeight()
            .background(IndiStrawTheme.colors.gray)
    )
}