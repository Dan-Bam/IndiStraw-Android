package com.danbam.indistraw.feature.mobile.movie.detail

import android.media.MediaMetadataRetriever
import android.view.WindowManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.ButtonMedium
import com.danbam.indistraw.core.design_system.component.FindPasswordMedium
import com.danbam.indistraw.core.design_system.component.ImageButton
import com.danbam.indistraw.core.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.component.Shape
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.component.TitleSemiBold
import com.danbam.indistraw.core.design_system.util.androidx.RemoveOverScrollLazyRow
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.util.androidx.getActivity
import com.danbam.indistraw.core.domain.entity.movie.MoviePeopleEntity
import com.danbam.indistraw.feature.mobile.movie.BuildConfig
import com.danbam.indistraw.feature.mobile.navigation.movie.MovieDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.movie.MovieNavigationItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieIdx: Long,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    getActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)

    val container = movieDetailViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val movieHeight = LocalConfiguration.current.screenHeightDp * 0.3

    LaunchedEffect(Unit) {
        movieDetailViewModel.movieDetail(movieIdx = movieIdx)
        movieDetailViewModel.movieHistory(movieIdx = movieIdx)
    }

    var selectedPeople: MoviePeopleEntity? by remember { mutableStateOf(null) }

    IndiStrawBottomSheetLayout(sheetContent = {
        selectedPeople?.let {
            Column(
                modifier = Modifier.align(CenterHorizontally),
                horizontalAlignment = CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(top = 31.dp)
                        .size(70.dp)
                        .clip(IndiStrawTheme.shapes.circle),
                    model = it.profileUrl,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
                FindPasswordMedium(
                    modifier = Modifier.padding(top = 4.dp),
                    text = it.name,
                    fontSize = 14
                )
            }
            ButtonMedium(
                modifier = Modifier.padding(start = 20.dp, top = 29.dp), text = stringResource(
                    id = R.string.participate_movie
                )
            )
            RemoveOverScrollLazyRow(
                modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(20.dp))
                }
                items(state.appearanceMovieList) {
                    ImageButton(
                        modifier = Modifier
                            .width(100.dp)
                            .height(90.dp),
                        imgSrc = it.thumbnailUrl,
                        shape = Shape.Rectangle
                    ) {
                        navController.navigate(MovieNavigationItem.Detail.route + MovieDeepLinkKey.MOVIE_INDEX + it.movieIdx)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
                item {
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }) { _, moreInfo ->
        IndiStrawColumnBackground(
            scrollEnabled = true
        ) {
            IndiStrawHeader(
                pressBackBtn = { navController.popBackStack() }
            )
            Box {
                ImageButton(
                    modifier = Modifier
                        .padding(top = 17.dp)
                        .height(movieHeight.dp)
                        .fillMaxWidth(),
                    imgSrc = state.movieDetailInfo.thumbnailUrl,
                    shape = Shape.None
                ) {
                    kotlin.runCatching {
                        MediaMetadataRetriever().apply {
                            setDataSource("${BuildConfig.VIDEO_PRE_PATH}${state.movieDetailInfo.movieUrl}")
                        }
                    }.onSuccess {
                        val isVertical =
                            (it.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)
                                ?.toInt() ?: 0 < it.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)
                                ?.toInt() ?: 0)
                        it.release()
                        navController.navigate(
                            MovieNavigationItem.Play.route + MovieDeepLinkKey.MOVIE_NAME + state.movieDetailInfo.title + MovieDeepLinkKey.MOVIE_INDEX + movieIdx + MovieDeepLinkKey.MOVIE_URL + state.movieDetailInfo.movieUrl + MovieDeepLinkKey.MOVIE_POSITION + state.moviePosition + MovieDeepLinkKey.IS_VERTICAL + isVertical
                        )
                    }
                }
                IndiStrawIcon(
                    modifier = Modifier.align(Alignment.Center),
                    icon = IndiStrawIconList.Play
                )
            }
            TitleSemiBold(
                modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 8.dp),
                text = state.movieDetailInfo.title,
                fontSize = 18
            )
            FindPasswordMedium(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                text = state.movieDetailInfo.description,
                color = IndiStrawTheme.colors.gray
            )
            TitleRegular(
                modifier = Modifier.padding(start = 15.dp, top = 44.dp), text = stringResource(
                    id = R.string.highlight
                )
            )
            RemoveOverScrollLazyRow(
                modifier = Modifier.padding(top = 14.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(15.dp))
                }
                items(state.movieDetailInfo.highlight) {
                    AsyncImage(
                        modifier = Modifier
                            .height(100.dp)
                            .width(160.dp)
                            .clip(IndiStrawTheme.shapes.smallRounded),
                        model = it,
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                item {
                    Spacer(modifier = Modifier.width(15.dp))
                }
            }
            ButtonMedium(
                modifier = Modifier.padding(top = 60.dp, start = 15.dp, bottom = 10.dp),
                text = stringResource(id = R.string.actor)
            )
            RemoveOverScrollLazyRow {
                item {
                    Spacer(modifier = Modifier.width(15.dp))
                }
                itemsIndexed(state.movieDetailInfo.directorList + state.movieDetailInfo.actorList) { index, item ->
                    Column(
                        modifier = Modifier.indiStrawClickable(onClick = {
                            selectedPeople = item
                            movieDetailViewModel.moviePeopleDetail(
                                isActor = index >= state.movieDetailInfo.directorList.size,
                                actorIdx = item.actorIdx
                            )
                            moreInfo()
                        }),
                        horizontalAlignment = CenterHorizontally
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(65.dp)
                                .clip(IndiStrawTheme.shapes.circle),
                            model = item.profileUrl,
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop
                        )
                        FindPasswordMedium(
                            modifier = Modifier.padding(top = 6.dp),
                            text = item.name
                        )
                        TitleRegular(
                            modifier = Modifier.padding(top = 4.dp),
                            text = stringResource(
                                id = if (index < state.movieDetailInfo.directorList.size) R.string.movie_director else R.string.movie_actor
                            ),
                            color = IndiStrawTheme.colors.gray,
                            fontSize = 12
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
                item {
                    Spacer(modifier = Modifier.width(15.dp))
                }
            }
        }
    }
}