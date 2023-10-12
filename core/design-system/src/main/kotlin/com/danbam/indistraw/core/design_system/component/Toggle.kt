package com.danbam.indistraw.core.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable

@Composable
fun IndiStrawToggle(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onChecked: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .background(
                if (isChecked) IndiStrawTheme.colors.main else IndiStrawTheme.colors.lightGray,
                IndiStrawTheme.shapes.bigRounded
            )
            .indiStrawClickable { onChecked(!isChecked) }
            .padding(3.dp),
    ) {
        Box(
            modifier = modifier
                .padding(if (isChecked) PaddingValues(start = 26.dp) else PaddingValues(end = 26.dp))
                .background(IndiStrawTheme.colors.white, IndiStrawTheme.shapes.circle)
                .size(20.dp)
                .align(if (isChecked) Alignment.CenterStart else Alignment.CenterEnd)
        )
    }
}