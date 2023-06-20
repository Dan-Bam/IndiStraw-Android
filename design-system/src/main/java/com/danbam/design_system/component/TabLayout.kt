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
fun IndiStrawTabRow(
    modifier: Modifier = Modifier,
    itemList: List<String>,
    tabHeader: List<@Composable () -> Unit>,
    moreData: () -> Unit,
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
    if (isCrowdFunding) {
        Spacer(modifier = Modifier.height(10.dp))
        repeat(4) {
            FundingItem {
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

@Composable
private fun MovieItem(
    onClickItem: () -> Unit,
) {
    ImageButton(
        modifier = Modifier
            .width(110.dp)
            .height(150.dp),
        imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
        shape = Shape.Rectangle,
        onClick = onClickItem
    )
}

@Composable
private fun FundingItem(
    onClickItem: () -> Unit,
) {
    val context = LocalContext.current
    var crowdFundImgHeight by remember { mutableStateOf(0) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .indiStrawClickable(onClick = onClickItem)
            .padding(horizontal = 15.dp)
            .background(
                color = IndiStrawTheme.colors.lightBlack,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .padding(start = 13.dp, end = 7.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7F)
                .onSizeChanged { crowdFundImgHeight = it.height }
        ) {
            Spacer(modifier = Modifier.height(7.dp))
            HeadLineBold(
                text = "존윅",
                fontSize = 16,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))
            TitleRegular(
                modifier = Modifier.height(50.dp),
                text = "과거에 진 빛을 갚아야만 하는 존 윅 처단하고 싶지 않은 표적을 어쩔수 없이 암살한다. 이후 후원자에게 배반을 당하는데....",
                color = IndiStrawTheme.colors.gray,
                maxLines = 3,
                fontSize = 12
            )
            Spacer(modifier = Modifier.height(20.dp))
            IndiStrawProgress(currentProgress = 70F)
        }
        Spacer(modifier = Modifier.width(8.dp))
        AsyncImage(
            modifier = Modifier
                .height(crowdFundImgHeight.toDp(context).dp)
                .align(CenterVertically)
                .clip(IndiStrawTheme.shapes.smallRounded),
            model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
            contentDescription = "crowdFundingImg",
            contentScale = ContentScale.Crop
        )
    }
}