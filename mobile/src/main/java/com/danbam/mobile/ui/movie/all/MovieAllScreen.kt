package com.danbam.mobile.ui.movie.all

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawGenreList
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.MovieGenre
import com.danbam.design_system.component.MovieItem
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.mobile.ui.movie.navigation.MovieNavigationItem

@Composable
fun MovieAllScreen(
    navController: NavController,
    movieAllViewModel: MovieAllViewModel = hiltViewModel()
) {
    val container = movieAllViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var currentGenre: MovieGenre by remember { mutableStateOf(MovieGenre.All) }
    val movieAllPager = state.movieAllPager?.collectAsLazyPagingItems()

    LaunchedEffect(currentGenre) {
        movieAllViewModel.movieList(currentGenre)
    }

    IndiStrawColumnBackground {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        IndiStrawGenreList(
            itemList = MovieGenre.toList(),
            selectedItem = currentGenre,
            onItemSelect = {
                currentGenre = it
            })
        Spacer(modifier = Modifier.height(20.dp))
        movieAllPager?.let { pager ->
            when (pager.loadState.refresh) {
                is LoadState.Loading -> {}
                is LoadState.Error -> {}

                else -> {
                    RemoveOverScrollLazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(pager.itemCount / 3) { rowCount ->
                            Row {
                                repeat(3) {
                                    pager[rowCount + it]?.let {
                                        MovieItem(item = it) {
                                            navController.navigate(MovieNavigationItem.Detail.route)
                                        }
                                    }
                                    if (it != 2) Spacer(modifier = Modifier.width(9.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}