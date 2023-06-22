package com.danbam.mobile.ui.main.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.ImageButton
import com.danbam.design_system.component.IndiStrawBanner
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTab
import com.danbam.design_system.component.IndiStrawTabRow
import com.danbam.design_system.component.MovieTab
import com.danbam.design_system.component.Shape
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.mobile.ui.movie.navigation.MovieNavigationItem
import com.danbam.mobile.ui.profile.navigation.ProfileNavigationItem
import com.danbam.mobile.ui.search.navigation.SearchNavigationItem
import com.danbam.mobile.util.view.findActivity
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val container = mainViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val context = LocalContext.current
    var currentMovieTab: MovieTab by remember { mutableStateOf(MovieTab.RecentMovie) }

    BackHandler {
        context.findActivity()?.finish()
    }

    LaunchedEffect(Unit) {
        mainViewModel.getProfile()
    }

    IndiStrawColumnBackground(
        scrollEnabled = true
    ) {
        IndiStrawHeader(
            marginTop = 6,
            isBackBtn = false
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IndiStrawIcon(modifier = Modifier.indiStrawClickable {
                    navController.navigate(
                        SearchNavigationItem.Search.route
                    )
                }, icon = IndiStrawIconList.Search)
                if (state.profileUrl != null) {
                    ImageButton(
                        modifier = Modifier
                            .padding(start = 26.dp)
                            .size(30.dp),
                        imgSrc = state.profileUrl,
                        shape = Shape.Circle
                    ) {
                        navController.navigate(ProfileNavigationItem.Profile.route)
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .indiStrawClickable { navController.navigate(ProfileNavigationItem.Profile.route) }
                            .padding(start = 26.dp)
                            .size(30.dp)
                            .background(
                                color = IndiStrawTheme.colors.gray,
                                shape = IndiStrawTheme.shapes.circle
                            )
                    ) {
                        IndiStrawIcon(
                            modifier = Modifier
                                .align(Alignment.Center),
                            icon = IndiStrawIconList.Profile,
                            contentScale = ContentScale.Crop
                        )
                    }
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
            ), moreData = {
                navController.navigate(MovieNavigationItem.MovieAll.route)
            }
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
        Spacer(
            modifier = Modifier
                .padding(top = 14.dp, bottom = 30.dp)
                .height(1.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray)
        )
        HeadLineBold(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .background(
                    color = IndiStrawTheme.colors.lightBlack,
                    shape = IndiStrawTheme.shapes.defaultRounded
                )
                .padding(vertical = 14.dp, horizontal = 20.dp),
            text = stringResource(id = R.string.make_indi_movie),
            fontSize = 20,
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.height(8.dp))
        HeadLineBold(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .background(
                    color = IndiStrawTheme.colors.lightBlack,
                    shape = IndiStrawTheme.shapes.defaultRounded
                )
                .padding(vertical = 14.dp, horizontal = 20.dp),
            text = stringResource(id = R.string.make_crowd_fund),
            fontSize = 20,
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}