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
import com.danbam.domain.entity.FundingEntity

@Composable
fun FundingItem(
    item: FundingEntity,
    onClickItem: () -> Unit,
) {
    val crowdFundingImageSize = LocalConfiguration.current.screenWidthDp * 0.3
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
            IndiStrawProgress(currentProgress = item.percentage.toFloat())
        }
    }
}