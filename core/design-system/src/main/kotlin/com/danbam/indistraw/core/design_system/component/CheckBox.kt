package com.danbam.indistraw.core.design_system.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable

@Composable
fun IndiStrawCheckBox(
    modifier: Modifier = Modifier,
    isBorder: Boolean = false,
    isCheck: Boolean,
    onClick: () -> Unit,
) {
    val checkIcon = if (isCheck) IndiStrawIconList.Check else IndiStrawIconList.UnCheck
    Box(
        modifier = if (isBorder) Modifier
            .border(
                width = 2.dp,
                color = IndiStrawTheme.colors.gray,
                shape = IndiStrawTheme.shapes.smallRounded
            ) else Modifier
    ) {
        IndiStrawIcon(
            modifier = modifier
                .padding(horizontal = 4.dp, vertical = 6.dp)
                .indiStrawClickable(onClick = onClick),
            icon = checkIcon
        )
    }
}