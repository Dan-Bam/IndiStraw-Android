package com.danbam.indistraw.feature.mobile.main.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.ImageButton
import com.danbam.indistraw.core.design_system.component.IndiStrawBanner
import com.danbam.indistraw.core.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.ui.component.IndiStrawColumnTab
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.ui.component.IndiStrawRowTab
import com.danbam.indistraw.core.ui.component.IndiStrawTab
import com.danbam.indistraw.core.ui.component.MovieTab
import com.danbam.indistraw.core.design_system.component.Shape
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.component.TitleSemiBold
import com.danbam.indistraw.core.design_system.util.android.findActivity
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.feature.mobile.navigation.funding.FundingDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.funding.FundingNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.movie.PeopleType
import com.danbam.indistraw.feature.mobile.navigation.movie.MovieDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.movie.MovieNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.profile.ProfileNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.search.SearchNavigationItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val container = mainViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val context = LocalContext.current
    var currentMovieTab: com.danbam.indistraw.core.ui.component.MovieTab by remember { mutableStateOf(
        com.danbam.indistraw.core.ui.component.MovieTab.PopularMovie) }

    BackHandler {
        context.findActivity()?.finish()
    }

    LaunchedEffect(Unit) {
        mainViewModel.getProfile()
        mainViewModel.fundingPopularLst()
        mainViewModel.getBanner()
    }

    LaunchedEffect(currentMovieTab) {
        mainViewModel.movieList(currentMovieTab)
    }

    IndiStrawBottomSheetLayout(sheetContent = {
        Divider(
            modifier = Modifier
                .padding(top = 53.dp)
                .height(1.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray2)
        )
        TitleRegular(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 20.dp)
                .indiStrawClickable { navController.navigate(MovieNavigationItem.SearchPeople.route + MovieDeepLinkKey.PEOPLE_TYPE + PeopleType.DIRECTOR.route + MovieDeepLinkKey.IS_ENROLL + true) },
            text = stringResource(id = R.string.enroll_search_director)
        )
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray2)
        )
        TitleRegular(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 20.dp)
                .indiStrawClickable { navController.navigate(MovieNavigationItem.WritePeople.route + MovieDeepLinkKey.PEOPLE_TYPE + PeopleType.DIRECTOR.route + MovieDeepLinkKey.IS_ENROLL + true) },
            text = stringResource(id = R.string.enroll_new_director)
        )
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray2)
        )
        TitleRegular(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 20.dp)
                .indiStrawClickable { navController.navigate(MovieNavigationItem.SearchPeople.route + MovieDeepLinkKey.PEOPLE_TYPE + PeopleType.ACTOR.route + MovieDeepLinkKey.IS_ENROLL + true) },
            text = stringResource(id = R.string.enroll_search_actor)
        )
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray2)
        )
        TitleRegular(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 20.dp)
                .indiStrawClickable { navController.navigate(MovieNavigationItem.WritePeople.route + MovieDeepLinkKey.PEOPLE_TYPE + PeopleType.ACTOR.route + MovieDeepLinkKey.IS_ENROLL + true) },
            text = stringResource(id = R.string.enroll_new_actor)
        )
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray2)
        )
        Spacer(modifier = Modifier.height(140.dp))
    }) { _, openBottomSheet ->
        IndiStrawColumnBackground(
            scrollEnabled = true
        ) {
            IndiStrawHeader(
                isBackBtn = false
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IndiStrawIcon(modifier = Modifier.indiStrawClickable {
                        navController.navigate(SearchNavigationItem.Search.route)
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
            IndiStrawBanner(itemCount = state.bannerList.size) {
                ImageButton(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth(),
                    imgSrc = state.bannerList[it].thumbnailUrl,
                    shape = Shape.Rectangle
                ) {
                }
            }
            com.danbam.indistraw.core.ui.component.IndiStrawRowTab(
                modifier = Modifier
                    .padding(start = 15.dp, top = 20.dp),
                itemList = state.movieList,
                tabHeader = {
                    com.danbam.indistraw.core.ui.component.IndiStrawTab(
                        text = stringResource(id = R.string.popular),
                        isSelect = currentMovieTab == com.danbam.indistraw.core.ui.component.MovieTab.PopularMovie
                    ) {
                        currentMovieTab =
                            com.danbam.indistraw.core.ui.component.MovieTab.PopularMovie
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    com.danbam.indistraw.core.ui.component.IndiStrawTab(
                        text = stringResource(id = R.string.recommend),
                        isSelect = currentMovieTab == com.danbam.indistraw.core.ui.component.MovieTab.RecommendMovie
                    ) {
                        currentMovieTab =
                            com.danbam.indistraw.core.ui.component.MovieTab.RecommendMovie
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    com.danbam.indistraw.core.ui.component.IndiStrawTab(
                        text = stringResource(id = R.string.recent),
                        isSelect = currentMovieTab == com.danbam.indistraw.core.ui.component.MovieTab.RecentMovie
                    ) {
                        currentMovieTab =
                            com.danbam.indistraw.core.ui.component.MovieTab.RecentMovie
                    }
                }, moreData = {
                    navController.navigate(MovieNavigationItem.All.route)
                }
            ) {
                navController.navigate(MovieNavigationItem.Detail.route + MovieDeepLinkKey.MOVIE_INDEX + it)
            }
            com.danbam.indistraw.core.ui.component.IndiStrawColumnTab(
                itemList = state.fundingPopularList,
                tabHeader = {
                    TitleSemiBold(
                        modifier = Modifier.padding(start = 15.dp, top = 28.dp),
                        text = stringResource(id = R.string.crowd_funding),
                        fontSize = 16
                    )
                },
                moreData = { navController.navigate(FundingNavigationItem.All.route) },
            ) {
                navController.navigate(FundingNavigationItem.Detail.route + FundingDeepLinkKey.FUNDING_INDEX + it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .background(
                        IndiStrawTheme.colors.lightBlack,
                        IndiStrawTheme.shapes.defaultRounded
                    )
                    .padding(vertical = 18.dp),
            ) {
                ExampleTextMedium(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 13.dp)
                        .indiStrawClickable { navController.navigate(MovieNavigationItem.WriteIntroduce.route) },
                    text = stringResource(id = R.string.make_indi_movie)
                )
                Divider(
                    modifier = Modifier
                        .padding(vertical = 18.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(IndiStrawTheme.colors.darkGray3)
                )
                ExampleTextMedium(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 13.dp)
                        .indiStrawClickable { navController.navigate(FundingNavigationItem.Make.route) },
                    text = stringResource(id = R.string.make_crowd_fund)
                )
                Divider(
                    modifier = Modifier
                        .padding(vertical = 18.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(IndiStrawTheme.colors.darkGray3)
                )
                ExampleTextMedium(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 13.dp)
                        .indiStrawClickable { openBottomSheet() },
                    text = stringResource(id = R.string.enroll_director_or_actor)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}