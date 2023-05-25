package com.danbam.design_system.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.indiStrawClickable

@Composable
fun IndiStrawHeader(
    modifier: Modifier = Modifier,
    marginTop: Int = 7,
    isBackBtn: Boolean = true,
    backStringId: Int? = null,
    pressBackBtn: () -> Unit = {},
    headerContent: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .padding(top = marginTop.dp)
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (isBackBtn) {
            Row(
                modifier = Modifier
                    .indiStrawClickable(pressBackBtn)
                    .align(CenterVertically)
            ) {
                IndiStrawIcon(
                    icon = IndiStrawIconList.Back
                )
                if (backStringId != null) {
                    Spacer(modifier = Modifier.width(10.dp))
                    TitleRegular(
                        text = stringResource(id = backStringId),
                        modifier = Modifier.align(CenterVertically)
                    )
                }
            }
        } else {
            HeadLineBold(text = "로고")
        }
        if (headerContent != null) {
            headerContent()
        }
    }
}