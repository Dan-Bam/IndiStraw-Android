package com.danbam.mobile.ui.profile.profile

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
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTab
import com.danbam.design_system.R
import com.danbam.design_system.component.FundingTab
import com.danbam.design_system.component.IndiStrawColumnTab
import com.danbam.design_system.component.IndiStrawRowTab
import com.danbam.design_system.component.MovieTab
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.mobile.ui.movie.navigation.MovieNavigationItem
import com.danbam.mobile.ui.profile.navigation.ProfileNavigationItem

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val container = profileViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var currentMovieTab: MovieTab by remember { mutableStateOf(MovieTab.RecentMovie) }
    var currentFundingTab: FundingTab by remember { mutableStateOf(FundingTab.ParticipantFunding) }

    LaunchedEffect(Unit) {
        profileViewModel.getProfile()
        profileViewModel.getParticipateFunding()
        profileViewModel.getMyFunding()
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
        IndiStrawRowTab(
            modifier = Modifier
                .padding(start = 15.dp, top = 32.dp),
            itemList = listOf(),
            tabHeader = {
                IndiStrawTab(
                    text = stringResource(id = R.string.recent_watch_movie),
                    isSelect = currentMovieTab == MovieTab.RecentMovie
                ) {
                    currentMovieTab = MovieTab.RecentMovie
                }
                Spacer(modifier = Modifier.width(16.dp))
                IndiStrawTab(
                    text = stringResource(id = R.string.participate_movie),
                    isSelect = currentMovieTab == MovieTab.ParticipantMovie
                ) {
                    currentMovieTab = MovieTab.ParticipantMovie
                }
            }
        ) {
            navController.navigate(MovieNavigationItem.Detail.route)
        }
        IndiStrawColumnTab(
            modifier = Modifier
                .padding(start = 15.dp, top = 43.dp),
            itemList = if (currentFundingTab == FundingTab.ParticipantFunding) state.fundingList else state.myFundingList,
            tabHeader = {
                IndiStrawTab(
                    text = stringResource(id = R.string.participate_funding),
                    isSelect = currentFundingTab == FundingTab.ParticipantFunding
                ) {
                    currentFundingTab = FundingTab.ParticipantFunding
                }
                if (state.myFundingList.isNotEmpty()) {
                    Spacer(modifier = Modifier.width(16.dp))
                    IndiStrawTab(
                        text = stringResource(id = R.string.my_funding),
                        isSelect = currentFundingTab == FundingTab.MyFunding
                    ) {
                        currentFundingTab = FundingTab.MyFunding
                    }
                }
            }) {
        }
    }
}