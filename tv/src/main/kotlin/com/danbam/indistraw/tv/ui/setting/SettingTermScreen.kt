package com.danbam.indistraw.tv.ui.setting

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
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.core.design_system.IndiStrawTheme

@Composable
fun SettingTermScreen(

) {
    Row {
        Column(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .fillMaxWidth(0.4F)
                .fillMaxHeight()
        ) {

        }
    }
    Box(
        modifier = Modifier
            .width(3.dp)
            .fillMaxHeight()
            .background(IndiStrawTheme.colors.gray)
    )
}