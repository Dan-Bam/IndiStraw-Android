package com.danbam.indistraw.design_system.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.design_system.IndiStrawTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IndiStrawBottomSheetLayout(
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable (ModalBottomSheetState, () -> Unit) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 13.dp)
            ) {
                Divider(
                    modifier = Modifier
                        .width(60.dp)
                        .height(3.dp)
                        .background(
                            color = IndiStrawTheme.colors.gray,
                            shape = IndiStrawTheme.shapes.smallRounded
                        )
                        .align(Alignment.CenterHorizontally)
                )
                sheetContent()
            }
        },
        sheetState = sheetState,
        sheetShape = IndiStrawTheme.shapes.bottomSheet,
        sheetBackgroundColor = IndiStrawTheme.colors.darkGray2
    ) {
        content(sheetState) {
            coroutineScope.launch {
                if (sheetState.isVisible) sheetState.hide()
                else sheetState.show()
            }
        }
    }
}