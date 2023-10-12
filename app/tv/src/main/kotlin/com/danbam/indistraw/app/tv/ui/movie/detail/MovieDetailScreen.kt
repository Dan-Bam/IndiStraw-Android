package com.danbam.indistraw.app.tv.ui.movie.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import coil.compose.AsyncImage
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.ButtonMedium
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.HeadLineBold
import com.danbam.indistraw.core.design_system.component.IndiStrawTvBackground
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.app.tv.ui.main.navigation.MainDeepLinkKey
import com.danbam.indistraw.app.tv.ui.main.navigation.MainNavigationItem

sealed class MovieTabItem(val stringId: Int) {
    object Highlight : MovieTabItem(R.string.highlight)
    object Actor : MovieTabItem(R.string.actor)
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieIdx: Long,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val container = movieDetailViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var currentTab: MovieTabItem by remember { mutableStateOf(MovieTabItem.Highlight) }

    LaunchedEffect(Unit) {
        movieDetailViewModel.movieDetail(movieIdx = movieIdx)
        movieDetailViewModel.movieHistory(movieIdx = movieIdx)
    }

    IndiStrawTvBackground {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6F)
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxWidth(0.6F)
                    .fillMaxHeight(),
                model = state.movieDetailInfo.thumbnailUrl,
                contentDescription = "movieThumbnail",
                contentScale = ContentScale.Crop,
                alpha = 0.7F
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .fillMaxHeight(0.9F)
                    .align(Alignment.TopStart)
                    .padding(start = 70.dp, top = 60.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    HeadLineBold(text = state.movieDetailInfo.title, fontSize = 50)
                    Spacer(modifier = Modifier.height(15.dp))
                    ExampleTextMedium(
                        text = state.movieDetailInfo.description,
                        fontSize = 18,
                        maxLines = 3
                    )
                }
                Row {
                    MoviePlayButton(
                        icon = IndiStrawIconList.FastPlay,
                        title = stringResource(id = R.string.watch_going)
                    ) {
                        navController.navigate(
                            MainNavigationItem.MoviePlay.route + MainDeepLinkKey.MOVIE_NAME + state.movieDetailInfo.title + MainDeepLinkKey.MOVIE_INDEX + movieIdx + MainDeepLinkKey.MOVIE_URL + state.movieDetailInfo.movieUrl + MainDeepLinkKey.MOVIE_POSITION + state.moviePosition
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    MoviePlayButton(
                        icon = IndiStrawIconList.PlayFirst,
                        title = stringResource(id = R.string.watch_first)
                    ) {
                        navController.navigate(
                            MainNavigationItem.MoviePlay.route + MainDeepLinkKey.MOVIE_NAME + state.movieDetailInfo.title + MainDeepLinkKey.MOVIE_INDEX + movieIdx + MainDeepLinkKey.MOVIE_URL + state.movieDetailInfo.movieUrl + MainDeepLinkKey.MOVIE_POSITION + 0F
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.padding(start = 70.dp)
        ) {
            MovieTab(MovieTabItem.Highlight, currentTab) {
                currentTab = it
            }
            Spacer(modifier = Modifier.width(19.dp))
            MovieTab(MovieTabItem.Actor, currentTab) {
                currentTab = it
            }
        }
        Divider(
            modifier = Modifier
                .padding(start = 70.dp, top = 10.dp, bottom = 15.dp)
                .height(2.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray)
        )
        when (currentTab) {
            is MovieTabItem.Highlight -> {
                TvLazyRow(
                    modifier = Modifier.padding(start = 70.dp),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp)
                ) {
                    items(state.movieDetailInfo.highlight) {
                        Surface(onClick = { }) {
                            AsyncImage(
                                modifier = Modifier
                                    .width(210.dp)
                                    .height(150.dp)
                                    .clip(IndiStrawTheme.shapes.smallRounded),
                                model = it,
                                contentDescription = "highlightImage",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            is MovieTabItem.Actor -> {
                TvLazyRow(
                    modifier = Modifier.padding(start = 70.dp),
                    horizontalArrangement = Arrangement.spacedBy(35.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp)
                ) {
                    items(state.movieDetailInfo.directorList + state.movieDetailInfo.actorList) {
                        Surface(
                            shape = ClickableSurfaceDefaults.shape(
                                shape = RoundedCornerShape(0.dp)
                            ),
                            color = ClickableSurfaceDefaults.color(
                                color = Color.Transparent,
                                focusedColor = Color.Transparent,
                                pressedColor = Color.Transparent,
                                disabledColor = Color.Transparent
                            ),
                            onClick = { }
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(150.dp),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(IndiStrawTheme.shapes.circle),
                                    model = it.profileUrl,
                                    contentDescription = "actorProfile",
                                    contentScale = ContentScale.Crop
                                )
                                HeadLineBold(text = it.name)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MoviePlayButton(
    icon: IndiStrawIconList,
    title: String,
    onClick: () -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.onFocusChanged {
            focused = it.hasFocus || it.isFocused
        },
        shape = ClickableSurfaceDefaults.shape(
            shape = IndiStrawTheme.shapes.defaultRounded
        ),
        scale = ClickableSurfaceDefaults.scale(
            focusedScale = 1F
        ),
        color = ClickableSurfaceDefaults.color(
            color = IndiStrawTheme.colors.darkGray3,
            focusedColor = IndiStrawTheme.colors.main,
            pressedColor = IndiStrawTheme.colors.main,
            disabledColor = IndiStrawTheme.colors.darkGray3
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 9.dp)
                .background(
                    if (focused) IndiStrawTheme.colors.main else IndiStrawTheme.colors.darkGray3,
                    IndiStrawTheme.shapes.defaultRounded
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IndiStrawIcon(modifier = Modifier.size(20.dp), icon = icon)
            Spacer(modifier = Modifier.width(10.dp))
            ButtonMedium(text = title)
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MovieTab(
    tab: MovieTabItem,
    currentTab: MovieTabItem,
    onClick: (MovieTabItem) -> Unit
) {
    Surface(
        shape = ClickableSurfaceDefaults.shape(
            shape = RoundedCornerShape(0.dp)
        ),
        color = ClickableSurfaceDefaults.color(
            color = Color.Transparent,
            focusedColor = Color.Transparent,
            pressedColor = Color.Transparent,
            disabledColor = Color.Transparent
        ),
        onClick = { onClick(tab) }
    ) {
        if (currentTab == tab) {
            HeadLineBold(text = stringResource(id = tab.stringId))
        } else {
            TitleRegular(
                text = stringResource(id = tab.stringId),
                fontSize = 24,
                color = IndiStrawTheme.colors.gray
            )
        }
    }
}