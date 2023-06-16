package com.danbam.presentation.ui.profile.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTab
import com.danbam.design_system.component.IndiStrawTabRow
import com.danbam.design_system.R
import com.danbam.design_system.component.FundingTab
import com.danbam.design_system.component.MovieTab
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.presentation.ui.movie.navigation.MovieNavigationItem
import com.danbam.presentation.ui.profile.navigation.ProfileNavigationItem

@Composable
fun ProfileScreen(
    navController: NavController,
) {
    var currentMovieTab: MovieTab by remember { mutableStateOf(MovieTab.RecentMovie) }
    var currentFundingTab: FundingTab by remember { mutableStateOf(FundingTab.ParticipantFunding) }

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
            if (false) {
                AsyncImage(
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(
                            shape = IndiStrawTheme.shapes.circle
                        ),
                    model = "", contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .background(
                            color = IndiStrawTheme.colors.gray,
                            shape = IndiStrawTheme.shapes.circle,
                        )
                        .padding(paddingValues = PaddingValues(23.dp)),
                ) {
                    IndiStrawIcon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(35.dp)
                            .height(35.dp),
                        icon = IndiStrawIconList.NoImage
                    )
                }
            }
        }
        HeadLineBold(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp),
            text = "이동욱님",
            fontSize = 20,
            textAlign = TextAlign.Center
        )
        IndiStrawTabRow(
            modifier = Modifier
                .padding(top = 32.dp, start = 15.dp),
            itemList = listOf(""),
            tabHeader = listOf(
                {
                    IndiStrawTab(
                        text = stringResource(id = R.string.recent_watch_movie),
                        isSelect = currentMovieTab == MovieTab.RecentMovie
                    ) {
                        currentMovieTab = MovieTab.RecentMovie
                    }
                }, {
                    IndiStrawTab(
                        text = stringResource(id = R.string.participate_movie),
                        isSelect = currentMovieTab == MovieTab.ParticipantMovie
                    ) {
                        currentMovieTab = MovieTab.ParticipantMovie
                    }
                }), moreData = { }
        ) {
            navController.navigate(MovieNavigationItem.MovieDetail.route)
        }
        IndiStrawTabRow(
            modifier = Modifier
                .padding(top = 43.dp, start = 15.dp),
            itemList = listOf(""),
            tabHeader = listOf(
                {
                    IndiStrawTab(
                        text = stringResource(id = R.string.participate_funding),
                        isSelect = currentFundingTab == FundingTab.ParticipantFunding
                    ) {
                        currentFundingTab = FundingTab.ParticipantFunding
                    }
                }, {
                    IndiStrawTab(
                        text = stringResource(id = R.string.my_funding),
                        isSelect = currentFundingTab == FundingTab.MyFunding
                    ) {
                        currentFundingTab = FundingTab.MyFunding
                    }
                }), moreData = { }, isCrowdFunding = true
        ) {
        }
    }
}