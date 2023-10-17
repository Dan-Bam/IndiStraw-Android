package com.danbam.indistraw.feature.tv.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import com.danbam.indistraw.core.design_system.component.ImageButton
import com.danbam.indistraw.core.design_system.component.IndiStrawTvBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawTvBanner
import com.danbam.indistraw.core.design_system.component.IndiStrawTvTab
import com.danbam.indistraw.core.design_system.component.Shape
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.component.MovieTab
import com.danbam.indistraw.core.design_system.component.MovieTvItem
import com.danbam.indistraw.feature.tv.navigation.main.MainDeepLinkKey
import com.danbam.indistraw.feature.tv.navigation.main.MainNavigationItem

@Composable
fun HomeScreen(
    navController: NavController,
    isOpenDrawer: Boolean,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val container = homeViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val itemFocusRequester = remember { FocusRequester() }
    var homeTab: MovieTab by remember { mutableStateOf(MovieTab.PopularMovie) }

    LaunchedEffect(Unit) {
        homeViewModel.getBanner()
    }

    LaunchedEffect(state.currentMovieIndex) {
        if (!isOpenDrawer && state.currentMovieIndex != null) {
            itemFocusRequester.requestFocus()
        }
    }

    LaunchedEffect(homeTab) {
        homeViewModel.movieList(homeTab)
    }

    IndiStrawTvBackground {
        IndiStrawTvBanner(itemCount = state.bannerList.size) {
            ImageButton(
                modifier = Modifier
                    .fillMaxWidth(),
                imgSrc = state.bannerList[it].thumbnailUrl,
                shape = Shape.Rectangle
            ) {
            }
        }
        Row(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            IndiStrawTvTab(
                text = stringResource(id = R.string.popular),
                isSelect = homeTab == MovieTab.PopularMovie
            ) {
                homeTab = MovieTab.PopularMovie
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
                text = stringResource(id = R.string.recent),
                isSelect = homeTab == MovieTab.RecentMovie
            ) {
                homeTab = MovieTab.RecentMovie
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        TvLazyRow(
            modifier = Modifier.padding(end = 50.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp)
        ) {
            items(state.movieList) {
                MovieTvItem(
                    modifier = Modifier.focusRequester(if (it.movieIdx == state.currentMovieIndex) itemFocusRequester else FocusRequester()),
                    item = it
                ) {
                    homeViewModel.saveCurrentIndex(it.movieIdx)
                    navController.navigate(MainNavigationItem.MovieDetail.route + MainDeepLinkKey.MOVIE_INDEX + it.movieIdx)
                }
            }
        }
    }
}