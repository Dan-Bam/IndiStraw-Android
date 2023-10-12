package com.danbam.indistraw.feature.mobile.search.result_search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.danbam.indistraw.app.mobile.ui.funding.navigation.FundingDeepLinkKey
import com.danbam.indistraw.app.mobile.ui.funding.navigation.FundingNavigationItem
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawTab
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.component.FundingItem
import com.danbam.indistraw.core.design_system.component.MovieItem
import com.danbam.indistraw.core.design_system.component.SearchTab
import com.danbam.indistraw.core.design_system.util.androidx.RemoveOverScrollLazyColumn
import com.danbam.indistraw.app.mobile.ui.movie.navigation.MovieDeepLinkKey
import com.danbam.indistraw.app.mobile.ui.movie.navigation.MovieNavigationItem

@Composable
fun ResultSearchScreen(
    navController: NavController,
    resultSearchViewModel: ResultSearchViewModel = hiltViewModel(),
    onClickAction: (() -> Unit),
    keyword: String,
) {
    val container = resultSearchViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var currentTab: SearchTab by remember { mutableStateOf(SearchTab.Movie) }
    val moviePager = state.moviePager?.collectAsLazyPagingItems()
    val fundingPager = state.fundingPager?.collectAsLazyPagingItems()

    LaunchedEffect(currentTab) {
        if (currentTab == SearchTab.Movie) {
            resultSearchViewModel.searchMovie(keyword = keyword)
        } else {
            resultSearchViewModel.searchFunding(keyword = keyword)
        }
    }

    IndiStrawColumnBackground(
        onClickAction = onClickAction
    ) {
        Row(
            modifier = Modifier.padding(start = 15.dp, top = 22.dp)
        ) {
            IndiStrawTab(
                text = stringResource(id = R.string.indi_movie),
                isSelect = currentTab == SearchTab.Movie
            ) {
                currentTab = SearchTab.Movie
            }
            Spacer(modifier = Modifier.width(16.dp))
            IndiStrawTab(
                text = stringResource(id = R.string.crowd_funding),
                isSelect = currentTab == SearchTab.Funding
            ) {
                currentTab = SearchTab.Funding
            }
        }
        when (currentTab) {
            is SearchTab.Movie -> {
                moviePager?.let {
                    when (it.loadState.refresh) {
                        is LoadState.Loading -> {}
                        is LoadState.Error -> {}
                        else -> {
                            Spacer(modifier = Modifier.height(11.dp))
                            LazyVerticalGrid(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                columns = GridCells.Fixed(3),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(it.itemSnapshotList.items) {
                                    MovieItem(item = it) {
                                        navController.navigate(MovieNavigationItem.Detail.route + MovieDeepLinkKey.MOVIE_INDEX + it)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            is SearchTab.Funding -> {
                fundingPager?.let {
                    when (it.loadState.refresh) {
                        is LoadState.Loading -> {}
                        is LoadState.Error -> {}
                        else -> {
                            Spacer(modifier = Modifier.height(11.dp))
                            RemoveOverScrollLazyColumn {
                                items(it) {
                                    it?.let {
                                        FundingItem(item = it) {
                                            navController.navigate(FundingNavigationItem.Detail.route + FundingDeepLinkKey.FUNDING_INDEX + it)
                                        }
                                        Spacer(modifier = Modifier.height(24.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}