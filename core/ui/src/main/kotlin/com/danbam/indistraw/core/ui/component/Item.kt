package com.danbam.indistraw.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import coil.compose.AsyncImage
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.HeadLineBold
import com.danbam.indistraw.core.design_system.component.ImageButton
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.IndiStrawProgress
import com.danbam.indistraw.core.design_system.component.PriceRegular
import com.danbam.indistraw.core.design_system.component.Shape
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.component.TitleSemiBold
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.util.internal.toCommaString
import com.danbam.indistraw.core.domain.entity.funding.FundingDetailEntity
import com.danbam.indistraw.core.domain.entity.funding.FundingEntity
import com.danbam.indistraw.core.domain.entity.funding.MyFundingEntity
import com.danbam.indistraw.core.domain.entity.movie.MovieEntity

sealed class RewardType {
    object Default : RewardType()
    object Expand : RewardType()
}

@Composable
fun FundingItem(
    item: FundingEntity,
    onClickItem: (Long) -> Unit,
) {
    val crowdFundingImageSize = LocalConfiguration.current.screenWidthDp * 0.3
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .indiStrawClickable(onClick = { onClickItem(item.idx) })
            .padding(horizontal = 15.dp)
            .background(
                color = IndiStrawTheme.colors.lightBlack,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .padding(12.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(crowdFundingImageSize.dp)
                .align(Alignment.CenterVertically)
                .clip(IndiStrawTheme.shapes.smallRounded),
            model = item.thumbnail,
            contentDescription = "crowdFundingImg",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .weight(1F)
                .height(crowdFundingImageSize.dp)
        ) {
            HeadLineBold(
                text = item.title,
                fontSize = 16,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))
            TitleRegular(
                text = item.description,
                color = IndiStrawTheme.colors.gray,
                maxLines = 2,
                fontSize = 12
            )
            Spacer(modifier = Modifier.weight(1F))
            IndiStrawProgress(currentProgress = item.percentage)
        }
    }
}

@Composable
fun MovieItem(
    item: MovieEntity,
    onClickItem: (Long) -> Unit,
) {
    ImageButton(
        modifier = Modifier
            .width(110.dp)
            .height(150.dp),
        imgSrc = item.thumbnailUrl,
        shape = Shape.Rectangle,
        onClick = { onClickItem(item.movieIdx) }
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieTvItem(
    modifier: Modifier = Modifier,
    item: MovieEntity,
    onClickItem: () -> Unit,
) {
    val movieHeight = LocalConfiguration.current.screenHeightDp * 0.35
    val movieWidth = LocalConfiguration.current.screenWidthDp * 0.13
    Surface(
        modifier = modifier
            .width(movieWidth.dp)
            .height(movieHeight.dp),
        shape = ClickableSurfaceDefaults.shape(
            shape = IndiStrawTheme.shapes.smallRounded
        ),
        border = ClickableSurfaceDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(2.dp, IndiStrawTheme.colors.main),
                shape = IndiStrawTheme.shapes.smallRounded
            )
        ),
        onClick = onClickItem
    ) {
        AsyncImage(
            model = item.thumbnailUrl,
            contentDescription = "moviePoster",
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RewardItem(
    rewardType: RewardType = RewardType.Default,
    item: FundingDetailEntity.RewardEntity,
    onClickItem: (FundingDetailEntity.RewardEntity) -> Unit = {},
    onDelete: (() -> Unit)? = null
) {
    val rewardImageSize = LocalConfiguration.current.screenWidthDp * 0.45
    when (rewardType) {
        is RewardType.Default -> {
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .background(
                        color = IndiStrawTheme.colors.darkGray,
                        shape = IndiStrawTheme.shapes.defaultRounded
                    )
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp, bottom = 20.dp)
                    .indiStrawClickable {
                        if (item.totalCount != 0L) {
                            onClickItem(item)
                        }
                    }
            ) {
                TitleSemiBold(
                    text = item.title,
                    fontSize = 16,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(8.dp))
                TitleRegular(
                    text = item.description,
                    color = IndiStrawTheme.colors.gray,
                    maxLines = 1,
                    fontSize = 14
                )
                Spacer(modifier = Modifier.height(25.dp))
                Row {
                    TitleSemiBold(text = "${item.price.toCommaString()}원")
                    item.totalCount?.let {
                        Spacer(modifier = Modifier.width(8.dp))
                        PriceRegular(
                            modifier = Modifier
                                .background(
                                    IndiStrawTheme.colors.main,
                                    IndiStrawTheme.shapes.smallRounded
                                )
                                .padding(horizontal = 5.dp, vertical = 1.dp),
                            text = "${it}${stringResource(id = R.string.reward_remain)}",
                            fontSize = 12,
                        )
                    }
                }
            }
        }

        is RewardType.Expand -> {
            val state = rememberPagerState {
                item.imageList.size
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            if (onDelete != null) PaddingValues(
                                end = 4.dp,
                                top = 4.dp
                            ) else PaddingValues()
                        )
                        .background(
                            if (onDelete != null) IndiStrawTheme.colors.darkGray3 else Color.Transparent,
                            IndiStrawTheme.shapes.bigRounded
                        )
                        .padding(if (onDelete != null) PaddingValues(12.dp) else PaddingValues())
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Box {
                        HorizontalPager(state = state) {
                            AsyncImage(
                                modifier = Modifier
                                    .clip(IndiStrawTheme.shapes.defaultRounded)
                                    .height(rewardImageSize.dp)
                                    .fillMaxWidth(),
                                model = item.imageList[state.currentPage],
                                contentDescription = "rewardThumbnail",
                                contentScale = ContentScale.Crop
                            )
                        }
                        TitleRegular(
                            modifier = Modifier
                                .alpha(0.7F)
                                .align(Alignment.BottomEnd)
                                .padding(end = 8.dp, bottom = 8.dp)
                                .background(
                                    IndiStrawTheme.colors.gray3,
                                    IndiStrawTheme.shapes.smallRounded
                                )
                                .padding(horizontal = 10.dp, vertical = 1.dp),
                            text = "${state.currentPage + 1} / ${item.imageList.size}"
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TitleSemiBold(text = item.title, fontSize = 16)
                    Spacer(modifier = Modifier.height(8.dp))
                    TitleRegular(
                        text = item.title,
                        fontSize = 14,
                        color = IndiStrawTheme.colors.gray
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        ExampleTextMedium(
                            text = "${item.price.toCommaString()}${stringResource(id = R.string.money_unit)}",
                            fontSize = 16
                        )
                        item.totalCount?.let {
                            Spacer(modifier = Modifier.width(8.dp))
                            PriceRegular(
                                modifier = Modifier
                                    .background(
                                        IndiStrawTheme.colors.main,
                                        IndiStrawTheme.shapes.smallRounded
                                    )
                                    .padding(horizontal = 5.dp, vertical = 1.dp),
                                text = "${it}${stringResource(id = R.string.reward_remain)}",
                                fontSize = 12,
                            )
                        }
                    }
                    if (onDelete == null) {
                        Spacer(modifier = Modifier.height(41.dp))
                        IndiStrawButton(text = stringResource(id = R.string.do_funding)) {
                            onClickItem(item)
                        }
                        Spacer(modifier = Modifier.height(35.dp))
                    }
                }
                onDelete?.let {
                    IndiStrawIcon(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .indiStrawClickable(onClick = onDelete),
                        icon = IndiStrawIconList.Delete
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyRewardItem(
    rewardType: RewardType = RewardType.Default,
    item: MyFundingEntity.RewardEntity,
    onClickItem: (MyFundingEntity.RewardEntity) -> Unit,
) {
    val rewardImageSize = LocalConfiguration.current.screenWidthDp * 0.45
    when (rewardType) {
        is RewardType.Default -> {
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .background(
                        color = IndiStrawTheme.colors.darkGray,
                        shape = IndiStrawTheme.shapes.defaultRounded
                    )
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp, bottom = 20.dp)
                    .indiStrawClickable { onClickItem(item) }
            ) {
                TitleSemiBold(
                    text = item.title,
                    fontSize = 16,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(8.dp))
                TitleRegular(
                    text = item.description,
                    color = IndiStrawTheme.colors.gray,
                    maxLines = 1,
                    fontSize = 14
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    TitleSemiBold(text = "${item.price.toCommaString()}원")
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(
                        modifier = Modifier
                            .background(
                                IndiStrawTheme.colors.gray3,
                                IndiStrawTheme.shapes.smallRounded
                            )
                            .padding(horizontal = 5.dp, vertical = 1.dp)
                    ) {
                        IndiStrawIcon(icon = IndiStrawIconList.People)
                        PriceRegular(
                            text = "${item.totalCount?.toCommaString()}",
                            fontSize = 12,
                        )
                    }
                }
            }
        }

        is RewardType.Expand -> {
            val state = rememberPagerState {
                item.imageList.size
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    Box {
                        HorizontalPager(state = state) {
                            AsyncImage(
                                modifier = Modifier
                                    .clip(IndiStrawTheme.shapes.defaultRounded)
                                    .height(rewardImageSize.dp)
                                    .fillMaxWidth(),
                                model = item.imageList[state.currentPage],
                                contentDescription = "rewardThumbnail",
                                contentScale = ContentScale.Crop
                            )
                        }
                        TitleRegular(
                            modifier = Modifier
                                .alpha(0.7F)
                                .align(Alignment.BottomEnd)
                                .padding(end = 8.dp, bottom = 8.dp)
                                .background(
                                    IndiStrawTheme.colors.gray3,
                                    IndiStrawTheme.shapes.smallRounded
                                )
                                .padding(horizontal = 10.dp, vertical = 1.dp),
                            text = "${state.currentPage + 1} / ${item.imageList.size}"
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TitleSemiBold(text = item.title, fontSize = 16)
                    Spacer(modifier = Modifier.height(8.dp))
                    TitleRegular(
                        text = item.title,
                        fontSize = 14,
                        color = IndiStrawTheme.colors.gray
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        ExampleTextMedium(
                            text = "${item.price.toCommaString()}${stringResource(id = R.string.money_unit)}",
                            fontSize = 16
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Row(
                            modifier = Modifier
                                .background(
                                    IndiStrawTheme.colors.gray3,
                                    IndiStrawTheme.shapes.smallRounded
                                )
                                .padding(horizontal = 5.dp, vertical = 1.dp)
                        ) {
                            IndiStrawIcon(icon = IndiStrawIconList.People)
                            PriceRegular(
                                text = "${item.totalCount?.toCommaString()}",
                                fontSize = 12,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FileItem(
    file: String? = null,
    openFile: (() -> Unit)? = null,
    isDelete: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .background(
                IndiStrawTheme.colors.darkGray,
                IndiStrawTheme.shapes.defaultRounded
            )
            .padding(horizontal = 13.dp, vertical = 17.dp)
            .indiStrawClickable {
                openFile?.let { openFile() }
            }
    ) {
        IndiStrawIcon(icon = IndiStrawIconList.Attached)
        Spacer(modifier = Modifier.width(8.dp))
        ExampleTextMedium(
            modifier = Modifier.fillMaxWidth(0.9F),
            text = file ?: stringResource(id = R.string.require_file),
            maxLines = 1
        )
        Spacer(modifier = Modifier.weight(1F))
        isDelete?.let {
            IndiStrawIcon(
                modifier = Modifier.indiStrawClickable(onClick = isDelete),
                icon = IndiStrawIconList.Minus
            )
        }
    }
}