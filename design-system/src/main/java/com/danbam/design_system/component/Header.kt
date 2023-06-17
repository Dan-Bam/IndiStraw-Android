package com.danbam.design_system.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.indiStrawClickable

@Composable
fun IndiStrawHeader(
    modifier: Modifier = Modifier,
    marginTop: Int = 25,
    isBackBtn: Boolean = true,
    isBackString: Boolean = true,
    pressBackBtn: (() -> Unit)? = null,
    headerContent: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .padding(top = marginTop.dp)
            .fillMaxWidth()
            .padding(horizontal = 21.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        if (isBackBtn) {
            Row(
                modifier = Modifier
                    .indiStrawClickable(onClick = pressBackBtn)
            ) {
                IndiStrawIcon(
                    icon = IndiStrawIconList.Back
                )
                if (isBackString) {
                    Spacer(modifier = Modifier.width(10.dp))
                    TitleRegular(
                        text = stringResource(id = R.string.back),
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