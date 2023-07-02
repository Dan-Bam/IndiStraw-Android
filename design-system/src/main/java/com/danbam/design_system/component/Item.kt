package com.danbam.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.design_system.util.toMoney
import com.danbam.domain.entity.FundingDetailEntity
import com.danbam.domain.entity.FundingEntity

sealed class MovieType {
    object Poster : MovieType()
    object Detail : MovieType()
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
    movieType: MovieType = MovieType.Poster,
    onClickItem: () -> Unit,
) {
    when (movieType) {
        is MovieType.Poster -> {
            ImageButton(
                modifier = Modifier
                    .width(110.dp)
                    .height(150.dp),
                imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                shape = Shape.Rectangle,
                onClick = onClickItem
            )
        }

        is MovieType.Detail -> {
            val movieImageSize = LocalConfiguration.current.screenWidthDp * 0.3
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .indiStrawClickable(onClick = onClickItem)
                    .padding(horizontal = 15.dp)
                    .background(
                        color = IndiStrawTheme.colors.lightBlack,
                        shape = IndiStrawTheme.shapes.defaultRounded
                    )
                    .padding(12.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(movieImageSize.dp)
                        .align(Alignment.CenterVertically)
                        .clip(IndiStrawTheme.shapes.smallRounded),
                    model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    contentDescription = "crowdFundingImg",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .height(movieImageSize.dp)
                ) {
                    HeadLineBold(
                        text = "ksjdfksfjksdfj",
                        fontSize = 16,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TitleRegular(
                        text = "안어ㅏ넝란얼",
                        color = IndiStrawTheme.colors.gray,
                        maxLines = 2,
                        fontSize = 12
                    )
                    Spacer(modifier = Modifier.weight(1F))
                }
            }
        }
    }
}

@Composable
fun RewardItem(
    item: FundingDetailEntity.RewardEntity
) {
    val rewardImageSize = LocalConfiguration.current.screenWidthDp * 0.3
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(
                color = IndiStrawTheme.colors.darkGray,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .padding(12.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(rewardImageSize.dp)
                .align(Alignment.CenterVertically)
                .clip(IndiStrawTheme.shapes.smallRounded),
            model = item.imageUrl,
            contentDescription = "crowdFundingImg",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .weight(1F)
                .height(rewardImageSize.dp)
        ) {
            HeadLineBold(
                text = item.title,
                fontSize = 14,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            TitleRegular(
                text = item.description,
                color = IndiStrawTheme.colors.gray,
                maxLines = 2,
                fontSize = 14
            )
            Spacer(modifier = Modifier.weight(1F))
            TitleSemiBold(text = "${item.price.toMoney()}원")
        }
    }
}