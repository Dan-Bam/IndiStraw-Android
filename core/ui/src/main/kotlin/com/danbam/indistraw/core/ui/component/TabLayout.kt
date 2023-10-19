package com.danbam.indistraw.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.component.TitleSemiBold
import com.danbam.indistraw.core.design_system.util.androidx.RemoveOverScrollLazyRow
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.util.internal.toDp
import com.danbam.indistraw.core.domain.entity.funding.FundingEntity
import com.danbam.indistraw.core.domain.entity.movie.MovieEntity
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
                .wrapContentSize()
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun IndiStrawTvTab(
    modifier: Modifier = Modifier,
    text: String,
    isSelect: Boolean,
    onSelect: () -> Unit,
) {
    val context = LocalContext.current
    var tabWidth by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = ClickableSurfaceDefaults.shape(
                shape = RoundedCornerShape(0.dp)
            ),
            color = ClickableSurfaceDefaults.color(
                color = Color.Transparent,
                focusedColor = Color.Transparent,
                pressedColor = Color.Transparent,
                disabledColor = Color.Transparent
            ),
            onClick = onSelect
        ) {
            ExampleTextMedium(
                modifier = modifier
                    .padding(bottom = 5.dp)
                    .onSizeChanged {
                        tabWidth = it.width.toDp(context)
                    },
                text = text,
                color = if (isSelect) IndiStrawTheme.colors.white else IndiStrawTheme.colors.darkGray3,
                fontSize = 35
            )
        }
        if (isSelect) {
            IndiStrawTabIndicator(
                tabWidth = (tabWidth + 10).dp,
                tabHeight = 3.dp
            )
        }
    }
}

@Composable
private fun IndiStrawTabIndicator(
    modifier: Modifier = Modifier,
    tabWidth: Dp,
    tabHeight: Dp = 1.dp
) {
    Spacer(
        modifier = modifier
            .height(tabHeight)
            .width(tabWidth)
            .background(
                color = IndiStrawTheme.colors.main,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
    )
}

@Composable
fun IndiStrawColumnTab(
    modifier: Modifier = Modifier,
    itemList: List<FundingEntity>,
    tabHeader: @Composable () -> Unit,
    moreData: (() -> Unit)? = null,
    onClickItem: (Long) -> Unit,
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
                tabHeader()
            }
            moreData?.let {
                TitleRegular(
                    modifier = Modifier
                        .indiStrawClickable(onClick = moreData)
                        .padding(end = 15.dp),
                    text = stringResource(id = R.string.view_all),
                    fontSize = 12,
                    color = IndiStrawTheme.colors.gray
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    repeat(itemList.size) {
        (itemList[it]).let { item ->
            FundingItem(item) {
                onClickItem(item.idx)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun IndiStrawRowTab(
    modifier: Modifier = Modifier,
    itemList: List<MovieEntity>,
    tabHeader: @Composable () -> Unit,
    moreData: (() -> Unit)? = null,
    onClickItem: (Long) -> Unit,
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
                tabHeader()
            }
            moreData?.let {
                TitleRegular(
                    modifier = Modifier
                        .indiStrawClickable(onClick = moreData)
                        .padding(end = 15.dp),
                    text = stringResource(id = R.string.view_all),
                    fontSize = 12,
                    color = IndiStrawTheme.colors.gray
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    RemoveOverScrollLazyRow(
        modifier = Modifier.padding(top = 10.dp),
        verticalAlignment = CenterVertically,
        state = state
    ) {
        item {
            Spacer(modifier = Modifier.width(15.dp))
        }
        items(itemList) {
            MovieItem(item = it) {
                onClickItem(it)
            }
            Spacer(modifier = Modifier.width(9.dp))
        }
    }
}