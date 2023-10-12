package com.danbam.indistraw.core.design_system.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable

@Composable
fun IndiStrawHeader(
    modifier: Modifier = Modifier,
    backIconSize: Int = 15,
    isBackBtn: Boolean = true,
    isBackString: Boolean = true,
    pressBackBtn: (() -> Unit)? = null,
    headerContent: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        if (isBackBtn) {
            Row(
                modifier = Modifier
                    .indiStrawClickable(onClick = pressBackBtn),
                verticalAlignment = CenterVertically
            ) {
                Box(
                    modifier = Modifier.width(28.dp)
                ) {
                    IndiStrawIcon(
                        modifier = Modifier
                            .size(backIconSize.dp)
                            .align(Center),
                        icon = IndiStrawIconList.Back
                    )
                }
                if (isBackString) {
                    Spacer(modifier = Modifier.width(4.dp))
                    TitleRegular(text = stringResource(id = R.string.back))
                }
            }
        } else {
            IndiStrawIcon(
                modifier = Modifier.size(50.dp),
                icon = IndiStrawIconList.Logo
            )
        }
        if (headerContent != null) {
            headerContent()
        }
    }
}