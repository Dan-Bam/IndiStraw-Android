package com.danbam.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.design_system.util.toDp
import com.danbam.domain.entity.FundingEntity
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

sealed class MovieTab {
    object RecentMovie : MovieTab()
    object ParticipantMovie : MovieTab()
    object RecommendMovie : MovieTab()
    object PopularMovie : MovieTab()
}

sealed class FundingTab {
    object ParticipantFunding : FundingTab()
    object MyFunding : FundingTab()
}

sealed class SearchTab {
    object Movie : SearchTab()
    object Funding : SearchTab()
}

@Composable
fun IndiStrawTab(
    modifier: Modifier = Modifier,
    text: String,
    isSelect: Boolean,
    onSelect: () -> Unit,
) {
    val context = LocalContext.current
    var tabWidth by remember { mutableStateOf(0) }
    Column {
        TitleSemiBold(
            modifier = modifier
                .padding(bottom = 5.dp)
                .wrapContentWidth()
                .wrapContentHeight()
                .indiStrawClickable(onClick = onSelect)
                .onSizeChanged {
                    tabWidth = it.width.toDp(context)
                },
            text = text,
            color = if (isSelect) IndiStrawTheme.colors.white else IndiStrawTheme.colors.darkGray3,
            fontSize = 16
        )
        if (isSelect) {
            IndiStrawTabIndicator(
                tabWidth = tabWidth.dp,
            )
        }
    }
}

@Composable
private fun IndiStrawTabIndicator(
    modifier: Modifier = Modifier,
    tabWidth: Dp,
) {
    Spacer(
        modifier = modifier
            .height(1.dp)
            .width(tabWidth)
            .background(
                color = IndiStrawTheme.colors.main,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
    )
}

@Composable
fun <T> IndiStrawTabRow(
    modifier: Modifier = Modifier,
    itemList: List<T>,
    tabHeader: List<@Composable () -> Unit>,
    moreData: (() -> Unit)? = null,
    isCrowdFunding: Boolean = false,
    onClickItem: (Int) -> Unit,
) {
    val state = rememberLazyListState()

    LaunchedEffect(itemList) {
        withContext(NonCancellable) {
            state.animateScrollToItem(0)
        }
    }

    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Row {
                tabHeader.forEach {
                    it()
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            moreData?.let {
                TitleRegular(
                    modifier = Modifier
                        .indiStrawClickable(onClick = moreData)
                        .padding(end = 15.dp, bottom = if (isCrowdFunding) 0.dp else 6.dp),
                    text = stringResource(id = R.string.view_all),
                    fontSize = 12,
                    color = IndiStrawTheme.colors.gray
                )
            }
        }
    }
    if (isCrowdFunding) {
        Spacer(modifier = Modifier.height(10.dp))
        repeat(itemList.size) {
            FundingItem(itemList[it] as FundingEntity) {
                onClickItem(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    } else {
        RemoveOverScrollLazyRow(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = CenterVertically,
            state = state
        ) {
            item {
                Spacer(modifier = Modifier.width(15.dp))
            }
            items(10) {
                MovieItem {
                    onClickItem(it)
                }
                Spacer(modifier = Modifier.width(9.dp))
            }
        }
    }
}