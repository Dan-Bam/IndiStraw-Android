package com.danbam.indistraw.core.design_system.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.core.design_system.IndiStrawTheme

@Composable
fun IndiStrawLoading(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier.size(80.dp),
        strokeWidth = 3.5.dp,
        color = IndiStrawTheme.colors.main
    )
}