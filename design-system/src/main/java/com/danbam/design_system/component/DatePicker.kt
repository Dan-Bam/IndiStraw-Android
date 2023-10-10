package com.danbam.design_system.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import java.time.LocalDate
import kotlin.math.abs

sealed class DatePicker {
    object Year : DatePicker()
    object Month : DatePicker()
    object Day : DatePicker()
}

@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    itemList: List<Int>,
    currentItem: Int,
    dateType: DatePicker,
    state: LazyListState = rememberLazyListState(),
    onScrollFinish: (index: Int) -> Unit,
) {
    val itemHeight = 50.dp
    val itemHalfHeightToPx = with(LocalDensity.current) { itemHeight.toPx() / 2 }

    val currentOnScrollFinish by rememberUpdatedState(onScrollFinish)

    LaunchedEffect(Unit) {
        state.animateScrollToItem(currentItem - if (dateType == DatePicker.Year) LocalDate.now().year else 1)
    }

    LaunchedEffect(state.isScrollInProgress) {
        if (!state.isScrollInProgress && state.firstVisibleItemScrollOffset != 0) {
            if (state.firstVisibleItemScrollOffset < itemHalfHeightToPx) {
                state.animateScrollToItem(state.firstVisibleItemIndex)
            } else if (state.firstVisibleItemScrollOffset > itemHalfHeightToPx) {
                state.animateScrollToItem(state.firstVisibleItemIndex + 1)
            }
        }
    }

    LaunchedEffect(state.firstVisibleItemIndex) {
        currentOnScrollFinish(itemList[state.firstVisibleItemIndex])
    }

    LaunchedEffect(state) {
        snapshotFlow { state.isScrollInProgress }
            .filter { !it && state.firstVisibleItemScrollOffset == 0 }
            .drop(1)
    }

    RemoveOverScrollLazyColumn(
        modifier = modifier
            .fillMaxWidth(
                when (dateType) {
                    DatePicker.Year -> 0.7F
                    DatePicker.Month -> 0.25F
                    DatePicker.Day -> 0.3F
                }
            )
            .height(itemHeight * 5),
        state = state,
        contentPadding = PaddingValues(vertical = itemHeight * (5 / 2)),
        horizontalAlignment = CenterHorizontally
    ) {
        items(itemList) {
            Box(
                modifier = Modifier
                    .height(itemHeight),
                contentAlignment = Alignment.Center
            ) {
                ButtonMedium(
                    text = "$it${
                        when (dateType) {
                            DatePicker.Year -> "년"
                            DatePicker.Month -> "월"
                            DatePicker.Day -> "일"
                        }
                    }",
                    fontSize = when (abs(currentItem - it)) {
                        0 -> 28
                        1 -> 24
                        else -> 20
                    },
                    color = when (abs(currentItem - it)) {
                        0 -> IndiStrawTheme.colors.white
                        1 -> IndiStrawTheme.colors.lightGray
                        else -> IndiStrawTheme.colors.gray2
                    }
                )
            }
        }
    }
}