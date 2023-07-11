package com.danbam.design_system.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.design_system.util.toCommaString
import com.danbam.domain.entity.FundingDetailEntity
import com.danbam.domain.entity.FundingEntity

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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieTvItem(
    modifier: Modifier = Modifier,
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
            model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
            contentDescription = "moviePoster",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun RewardItem(
    item: FundingDetailEntity.RewardEntity,
    onDelete: (() -> Unit)? = null
) {
    val rewardImageSize = LocalConfiguration.current.screenWidthDp * 0.3
    Box(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(
                    if (onDelete != null) {
                        PaddingValues(top = 4.dp, end = 4.dp)
                    } else PaddingValues()
                )
                .fillMaxWidth()
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
                TitleSemiBold(text = "${item.price.toCommaString()}원")
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