package com.danbam.indistraw.feature.mobile.profile.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.HeadLineBold
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.ui.component.IndiStrawTab
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.ui.component.FundingTab
import com.danbam.indistraw.core.ui.component.IndiStrawColumnTab
import com.danbam.indistraw.core.ui.component.IndiStrawRowTab
import com.danbam.indistraw.core.ui.component.MovieTab
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.feature.mobile.navigation.funding.FundingDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.funding.FundingNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.movie.MovieDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.movie.MovieNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.profile.ProfileNavigationItem

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val container = profileViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var currentMovieTab: com.danbam.indistraw.core.ui.component.MovieTab by remember { mutableStateOf(
        com.danbam.indistraw.core.ui.component.MovieTab.RecentMovie) }
    var currentFundingTab: com.danbam.indistraw.core.ui.component.FundingTab by remember { mutableStateOf(
        com.danbam.indistraw.core.ui.component.FundingTab.ParticipantFunding) }

    LaunchedEffect(Unit) {
        profileViewModel.getProfile()
        profileViewModel.getParticipateFunding()
        profileViewModel.getMyFunding()
        profileViewModel.movieHistory()
        profileViewModel.movieFilmography()
    }

    IndiStrawColumnBackground(
        scrollEnabled = true
    ) {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }) {
            Row {
                IndiStrawIcon(modifier = Modifier.indiStrawClickable {
                    navController.navigate(ProfileNavigationItem.Setting.route)
                }, icon = IndiStrawIconList.Setting)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if (state.profileUrl != null) {
                AsyncImage(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(
                            shape = IndiStrawTheme.shapes.circle
                        ),
                    model = state.profileUrl, contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else {
                IndiStrawIcon(
                    modifier = Modifier
                        .background(
                            color = IndiStrawTheme.colors.gray,
                            shape = IndiStrawTheme.shapes.circle,
                        )
                        .padding(23.dp)
                        .size(35.dp),
                    icon = IndiStrawIconList.Profile,
                )
            }
        }
        HeadLineBold(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp),
            text = state.name,
            fontSize = 20,
            textAlign = TextAlign.Center
        )
        com.danbam.indistraw.core.ui.component.IndiStrawRowTab(
            modifier = Modifier
                .padding(start = 15.dp, top = 32.dp),
            itemList = if (currentMovieTab == com.danbam.indistraw.core.ui.component.MovieTab.RecentMovie) state.movieHistoryList else state.movieFilmographyList,
            tabHeader = {
                com.danbam.indistraw.core.ui.component.IndiStrawTab(
                    text = stringResource(id = R.string.recent_watch_movie),
                    isSelect = currentMovieTab == com.danbam.indistraw.core.ui.component.MovieTab.RecentMovie
                ) {
                    currentMovieTab = com.danbam.indistraw.core.ui.component.MovieTab.RecentMovie
                }
                Spacer(modifier = Modifier.width(16.dp))
                com.danbam.indistraw.core.ui.component.IndiStrawTab(
                    text = stringResource(id = R.string.participate_movie),
                    isSelect = currentMovieTab == com.danbam.indistraw.core.ui.component.MovieTab.ParticipantMovie
                ) {
                    currentMovieTab =
                        com.danbam.indistraw.core.ui.component.MovieTab.ParticipantMovie
                }
            }
        ) {
            navController.navigate(MovieNavigationItem.Detail.route + MovieDeepLinkKey.MOVIE_INDEX + it)
        }
        com.danbam.indistraw.core.ui.component.IndiStrawColumnTab(
            modifier = Modifier
                .padding(start = 15.dp, top = 43.dp),
            itemList = if (currentFundingTab == com.danbam.indistraw.core.ui.component.FundingTab.ParticipantFunding) state.fundingList else state.myFundingList,
            tabHeader = {
                com.danbam.indistraw.core.ui.component.IndiStrawTab(
                    text = stringResource(id = R.string.participate_funding),
                    isSelect = currentFundingTab == com.danbam.indistraw.core.ui.component.FundingTab.ParticipantFunding
                ) {
                    currentFundingTab =
                        com.danbam.indistraw.core.ui.component.FundingTab.ParticipantFunding
                }
                if (state.myFundingList.isNotEmpty()) {
                    Spacer(modifier = Modifier.width(16.dp))
                    com.danbam.indistraw.core.ui.component.IndiStrawTab(
                        text = stringResource(id = R.string.my_funding),
                        isSelect = currentFundingTab == com.danbam.indistraw.core.ui.component.FundingTab.MyFunding
                    ) {
                        currentFundingTab =
                            com.danbam.indistraw.core.ui.component.FundingTab.MyFunding
                    }
                }
            }) {
            if (currentFundingTab == com.danbam.indistraw.core.ui.component.FundingTab.ParticipantFunding) {
                navController.navigate(FundingNavigationItem.Detail.route + FundingDeepLinkKey.FUNDING_INDEX + it)
            } else {
                navController.navigate(FundingNavigationItem.MyDetail.route + FundingDeepLinkKey.FUNDING_INDEX + it)
            }
        }
    }
}