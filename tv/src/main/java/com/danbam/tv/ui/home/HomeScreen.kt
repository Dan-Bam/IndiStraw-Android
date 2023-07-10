package com.danbam.tv.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import com.danbam.design_system.component.ImageButton
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.component.IndiStrawTvBanner
import com.danbam.design_system.component.IndiStrawTvTab
import com.danbam.design_system.component.Shape
import com.danbam.design_system.R
import com.danbam.design_system.component.MovieTab
import com.danbam.design_system.component.MovieTvItem
import com.danbam.design_system.util.RemoveOverScrollLazyRow

@Composable
fun HomeScreen(

) {
    var homeTab: MovieTab by remember { mutableStateOf(MovieTab.RecentMovie) }
    IndiStrawTvBackground {
        IndiStrawTvBanner(itemCount = 5) {
            ImageButton(
                modifier = Modifier
                    .fillMaxWidth(),
                imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                shape = Shape.Rectangle
            ) {
            }
        }
        Row(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            IndiStrawTvTab(
                text = stringResource(id = R.string.recent),
                isSelect = homeTab == MovieTab.RecentMovie
            ) {
                homeTab = MovieTab.RecentMovie
            }
            Spacer(modifier = Modifier.width(14.dp))
            IndiStrawTvTab(
                text = stringResource(id = R.string.recommend),
                isSelect = homeTab == MovieTab.RecommendMovie
            ) {
                homeTab = MovieTab.RecommendMovie
            }
            Spacer(modifier = Modifier.width(14.dp))
            IndiStrawTvTab(
                text = stringResource(id = R.string.popular),
                isSelect = homeTab == MovieTab.PopularMovie
            ) {
                homeTab = MovieTab.PopularMovie
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        TvLazyRow(
            modifier = Modifier.padding(end = 50.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                MovieTvItem {

                }
            }
        }
    }
}