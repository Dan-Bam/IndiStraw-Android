package com.danbam.presentation.ui.main.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.ImageButton
import com.danbam.design_system.component.IndiStrawBanner
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTab
import com.danbam.design_system.component.IndiStrawTabRow
import com.danbam.design_system.component.MovieTab
import com.danbam.design_system.component.Shape
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.presentation.ui.movie.navigation.MovieNavigationItem
import com.danbam.presentation.ui.profile.navigation.ProfileNavigationItem
import com.danbam.presentation.util.view.findActivity
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    var currentMovieTab: MovieTab by remember { mutableStateOf(MovieTab.RecentMovie) }

    BackHandler {
        context.findActivity()?.finish()
    }

    IndiStrawColumnBackground(
        scrollEnabled = true
    ) {
        IndiStrawHeader(
            isBackBtn = false
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IndiStrawIcon(icon = IndiStrawIconList.Search)
                ImageButton(
                    modifier = Modifier
                        .padding(start = 26.dp)
                        .width(30.dp)
                        .height(30.dp),
                    imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    shape = Shape.Circle
                ) {
                    navController.navigate(ProfileNavigationItem.Profile.route)
                }
            }
        }
        IndiStrawBanner(itemCount = 4) {
            ImageButton(
                imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                shape = Shape.Rectangle
            ) {
            }
        }
        IndiStrawTabRow(
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp),
            itemList = listOf(""),
            tabHeader = listOf(
                {
                    IndiStrawTab(
                        text = stringResource(id = R.string.recent),
                        isSelect = currentMovieTab == MovieTab.RecentMovie
                    ) {
                        currentMovieTab = MovieTab.RecentMovie
                    }
                },
                {
                    IndiStrawTab(
                        text = stringResource(id = R.string.recommend),
                        isSelect = currentMovieTab == MovieTab.RecommendMovie
                    ) {
                        currentMovieTab = MovieTab.RecommendMovie
                    }
                },
                {
                    IndiStrawTab(
                        text = stringResource(id = R.string.popular),
                        isSelect = currentMovieTab == MovieTab.PopularMovie
                    ) {
                        currentMovieTab = MovieTab.PopularMovie
                    }
                }
            ), moreData = { }
        ) {
            navController.navigate(MovieNavigationItem.MovieDetail.route)
        }
        IndiStrawTabRow(
            itemList = listOf(),
            tabHeader = listOf {
                TitleSemiBold(
                    modifier = Modifier.padding(start = 15.dp, top = 28.dp),
                    text = stringResource(id = R.string.crowd_funding),
                    fontSize = 16
                )
            },
            moreData = { },
            isCrowdFunding = true
        ) {
        }
    }
}