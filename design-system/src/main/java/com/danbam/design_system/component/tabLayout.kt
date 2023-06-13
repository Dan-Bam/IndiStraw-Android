package com.danbam.design_system.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import com.danbam.design_system.util.indiStrawClickable
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext


@Composable
fun IndiStrawTab(
    modifier: Modifier = Modifier,
    text: String,
    isSelect: Boolean,
    onSelect: () -> Unit,
) {
    TitleSemiBold(
        modifier = modifier
            .padding(bottom = 5.dp)
            .wrapContentWidth()
            .wrapContentHeight()
            .indiStrawClickable(onClick = onSelect),
        text = text,
        color = if (isSelect) IndiStrawTheme.colors.text else IndiStrawTheme.colors.line,
        fontSize = 16
    )
}

@Composable
private fun IndiStrawTabIndicator(
    modifier: Modifier = Modifier,
    tabWidth: Dp,
    indicatorOffset: Dp,
) {
    Spacer(
        modifier = modifier
            .height(1.dp)
            .width(tabWidth)
            .offset(x = indicatorOffset)
            .background(
                color = IndiStrawTheme.colors.main,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
    )
}

@Composable
fun IndiStrawTabRow(
    modifier: Modifier = Modifier,
    itemList: List<String>,
    tabWidthList: List<Int>,
    tabHeader: @Composable () -> Unit,
    moreData: () -> Unit,
    isCrowdFunding: Boolean = false,
    selectedIndex: Int,
) {
    val state = rememberLazyListState()

    LaunchedEffect(itemList) {
        withContext(NonCancellable) {
            state.animateScrollToItem(0)
        }
    }

    val indicatorOffset: Dp by animateDpAsState(
        targetValue = (
                tabWidthList.slice(0 until selectedIndex).sum() + (12 * selectedIndex)
                ).dp,
        animationSpec = tween(durationMillis = 200, easing = LinearEasing),
    )

    Column(
        modifier = modifier
    ) {
        Row {
            tabHeader()
        }
        IndiStrawTabIndicator(
            tabWidth = tabWidthList[selectedIndex].dp,
            indicatorOffset = indicatorOffset
        )
    }
    RemoveOverScrollLazyRow(
        modifier = Modifier.padding(top = 15.dp),
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