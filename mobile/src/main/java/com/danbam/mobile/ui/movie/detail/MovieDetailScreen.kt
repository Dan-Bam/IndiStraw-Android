package com.danbam.mobile.ui.movie.detail

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
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.ButtonMedium
import com.danbam.design_system.component.FindPasswordMedium
import com.danbam.design_system.component.ImageButton
import com.danbam.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.Shape
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.design_system.R
import com.danbam.domain.entity.MoviePeopleEntity
import com.danbam.mobile.ui.movie.navigation.MovieDeepLinkKey
import com.danbam.mobile.ui.movie.navigation.MovieNavigationItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieIndex: Int,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val container = movieDetailViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val movieHeight = LocalConfiguration.current.screenHeightDp * 0.3

    LaunchedEffect(Unit) {
        movieDetailViewModel.movieDetail(movieIndex = movieIndex)
    }

    var selectedPeople: MoviePeopleEntity? by remember { mutableStateOf(null) }
    var isActor by remember { mutableStateOf(false) }

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
                        navController.navigate(MovieNavigationItem.Detail.route + MovieDeepLinkKey.MOVIE_INDEX + it.idx)
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
                    navController.navigate(
                        MovieNavigationItem.Play.route + MovieDeepLinkKey.MOVIE_INDEX + movieIndex + MovieDeepLinkKey.MOVIE_URL + state.movieDetailInfo.movieUrl.split(
                            "/"
                        ).last() + MovieDeepLinkKey.MOVIE_POSITION + 0F
                    )
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
                                index >= state.movieDetailInfo.directorList.size,
                                item.idx
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