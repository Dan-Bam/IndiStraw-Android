package com.danbam.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import com.danbam.design_system.util.indiStrawClickable
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

sealed class MovieChip {
    object RecentMovie : MovieChip()
    object ParticipantMovie : MovieChip()
}

sealed class FundingChip {
    object ParticipantFunding : FundingChip()
    object MyFunding : FundingChip()
}

@Composable
fun IndiStrawChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelect: Boolean,
    onSelect: () -> Unit,
) {
    TitleRegular(
        modifier = modifier
            .background(
                color = if (isSelect) IndiStrawTheme.colors.main else IndiStrawTheme.colors.darkGray,
                shape = IndiStrawTheme.shapes.smallRounded
            )
            .indiStrawClickable(onClick = onSelect)
            .padding(horizontal = 11.dp, vertical = 4.dp), text = text
    )
}

@Composable
fun IndiStrawChipList(
    itemList: List<String>,
    chipHeader: @Composable () -> Unit,
    moreData: () -> Unit,
    isCrowdFunding: Boolean = false,
) {
    val state = rememberLazyListState()

    LaunchedEffect(itemList) {
        withContext(NonCancellable) {
            state.animateScrollToItem(0)
        }
    }

    chipHeader()
    RemoveOverScrollLazyRow(
        modifier = Modifier.padding(top = 28.dp),
        verticalAlignment = Alignment.CenterVertically,
        state = state
    ) {
        item {
            Spacer(modifier = Modifier.width(15.dp))
        }
        items(itemList.size) {
            Column {
                ImageButton(
                    modifier = Modifier
                        .width(if (isCrowdFunding) 120.dp else 110.dp)
                        .height(if (isCrowdFunding) 100.dp else 150.dp)
                        .background(Color.White),
                    imgSrc = it.toString(),
                    shape = Shape.Rectangle
                ) {

                }
            }
            Spacer(modifier = Modifier.width(9.dp))
        }
        item {
            IndiStrawIcon(
                modifier = Modifier
                    .indiStrawClickable(onClick = moreData)
                    .padding(start = 30.dp, end = 44.dp),
                icon = IndiStrawIconList.Plus
            )
        }
    }
}