package com.danbam.tv.ui.movie.movie

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.component.MovieGenre
import com.danbam.design_system.component.MovieTvItem
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.tv.ui.main.navigation.MainDeepLinkKey
import com.danbam.tv.ui.main.navigation.MainNavigationItem

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieScreen(
    navController: NavController,
    isOpenDrawer: Boolean,
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val container = movieViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val tabWidth = LocalConfiguration.current.screenWidthDp * 0.13
    val itemFocusRequester = remember { FocusRequester() }
    var currentMovieGenre: MovieGenre by remember { mutableStateOf(MovieGenre.All) }
    val movieAllPager = state.movieAllPager?.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        if (!isOpenDrawer) {
            itemFocusRequester.requestFocus()
        }
    }

    LaunchedEffect(currentMovieGenre) {
        movieViewModel.movieList(movieGenre = currentMovieGenre)
    }

    IndiStrawTvBackground {
        TvLazyRow(
            modifier = Modifier.padding(top = 90.dp, end = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(MovieGenre.toList()) {
                Surface(
                    scale = ClickableSurfaceDefaults.scale(
                        focusedScale = 1F
                    ),
                    shape = ClickableSurfaceDefaults.shape(
                        shape = IndiStrawTheme.shapes.smallRounded
                    ),
                    border = ClickableSurfaceDefaults.border(
                        border = Border(
                            border = BorderStroke(
                                if (currentMovieGenre == it) 0.dp else 1.dp,
                                IndiStrawTheme.colors.gray
                            ),
                            shape = IndiStrawTheme.shapes.smallRounded
                        ),
                        focusedBorder = Border.None
                    ),
                    color = ClickableSurfaceDefaults.color(
                        color = if (currentMovieGenre == it) IndiStrawTheme.colors.main else IndiStrawTheme.colors.navy,
                        focusedColor = IndiStrawTheme.colors.main,
                        pressedColor = IndiStrawTheme.colors.main,
                        disabledColor = IndiStrawTheme.colors.navy
                    ),
                    onClick = {
                        currentMovieGenre = it
                    }
                ) {
                    if (currentMovieGenre == it) {
                        TitleSemiBold(
                            modifier = Modifier
                                .width(tabWidth.dp)
                                .padding(vertical = 10.dp),
                            text = "${if (it == MovieGenre.All) "" else "#"}${stringResource(id = it.stringId)}",
                            fontSize = 24,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        TitleRegular(
                            modifier = Modifier
                                .width(tabWidth.dp)
                                .padding(vertical = 10.dp),
                            text = "${if (it == MovieGenre.All) "" else "#"}${stringResource(id = it.stringId)}",
                            fontSize = 24,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        movieAllPager?.let { pager ->
            when (pager.loadState.refresh) {
                is LoadState.Loading -> {}
                is LoadState.Error -> {}
                else -> {
                    TvLazyVerticalGrid(
                        modifier = Modifier.padding(top = 20.dp, end = 40.dp),
                        columns = TvGridCells.Fixed(6),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        items(pager.itemSnapshotList.items) {
                            MovieTvItem(
                                modifier = Modifier.focusRequester(if (it.movieIdx == state.currentMovieIndex) itemFocusRequester else FocusRequester()),
                                item = it
                            ) {
                                movieViewModel.saveCurrentIndex(it.movieIdx)
                                navController.navigate(MainNavigationItem.MovieDetail.route + MainDeepLinkKey.MOVIE_INDEX + it.movieIdx)
                            }
                        }
                    }
                }
            }
        }
    }
}