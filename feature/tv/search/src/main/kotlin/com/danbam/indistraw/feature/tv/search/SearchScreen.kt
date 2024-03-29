package com.danbam.indistraw.feature.tv.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.IndiStrawTvBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawTvTextField
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.HeadLineBold
import com.danbam.indistraw.core.ui.component.MovieTvItem
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.feature.tv.navigation.main.MainDeepLinkKey
import com.danbam.indistraw.feature.tv.navigation.main.MainNavigationItem

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val container = searchViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var search by remember { mutableStateOf("") }
    val moviePager = state.resultPager?.collectAsLazyPagingItems()

    LaunchedEffect(search) {
        if (search.isEmpty()) {
            searchViewModel.getRecentSearch()
        } else {
            searchViewModel.getRelatedSearch(keyword = search)
        }
    }

    LaunchedEffect(Unit) {
        searchViewModel.moviePopularList()
    }

    IndiStrawTvBackground {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.35F)
                    .padding(top = 90.dp)
            ) {
                IndiStrawTvTextField(
                    modifier = Modifier.fillMaxWidth(),
                    hint = stringResource(id = R.string.looking_for),
                    value = search,
                    onValueChange = { search = it },
                    imeAction = ImeAction.Search,
                    keyboardActions = KeyboardActions(onSearch = {
                        searchViewModel.search(keyword = search)
                    })
                )
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    if (search.isEmpty()) HeadLineBold(text = stringResource(id = R.string.recent_search))
                    Spacer(modifier = Modifier.height(10.dp))
                    TvLazyColumn(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(state.relatedSearchList) {
                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                scale = ClickableSurfaceDefaults.scale(
                                    focusedScale = 1F
                                ),
                                color = ClickableSurfaceDefaults.color(
                                    color = Color.Transparent,
                                    focusedColor = Color.Transparent,
                                    pressedColor = Color.Transparent,
                                    disabledColor = Color.Transparent
                                ),
                                border = ClickableSurfaceDefaults.border(
                                    focusedBorder = Border(
                                        border = BorderStroke(2.dp, IndiStrawTheme.colors.main),
                                        shape = IndiStrawTheme.shapes.smallRounded
                                    )
                                ),
                                onClick = {
                                    search = it
                                    searchViewModel.search(keyword = search)
                                }
                            ) {
                                TitleRegular(
                                    modifier = Modifier.padding(
                                        horizontal = 12.dp,
                                        vertical = 4.dp
                                    ),
                                    text = it,
                                    fontSize = 24,
                                    color = IndiStrawTheme.colors.gray
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(30.dp))
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(top = 60.dp, end = 40.dp)
            ) {
                ExampleTextMedium(
                    text = stringResource(id = R.string.popular_search_movie),
                    fontSize = 35,
                    color = IndiStrawTheme.colors.gray
                )
                Spacer(modifier = Modifier.height(10.dp))
                TvLazyVerticalGrid(
                    columns = TvGridCells.Fixed(4),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp)
                ) {
                    if (moviePager != null) {
                        when (moviePager.loadState.refresh) {
                            is LoadState.Loading -> {}
                            is LoadState.Error -> {}
                            else -> {
                                items(moviePager.itemSnapshotList.items) {
                                    com.danbam.indistraw.core.ui.component.MovieTvItem(item = it) {
                                        navController.navigate(MainNavigationItem.MovieDetail.route + MainDeepLinkKey.MOVIE_INDEX + it.movieIdx)
                                    }
                                }
                            }
                        }
                    } else {
                        items(state.moviePopularList) {
                            com.danbam.indistraw.core.ui.component.MovieTvItem(item = it) {
                                navController.navigate(MainNavigationItem.MovieDetail.route + MainDeepLinkKey.MOVIE_INDEX + it.movieIdx)
                            }
                        }
                    }
                }
            }
        }
    }
}