package com.danbam.mobile.ui.search.result_search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawTab
import com.danbam.design_system.R
import com.danbam.design_system.component.FundingItem
import com.danbam.design_system.component.MovieItem
import com.danbam.design_system.component.SearchTab
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.domain.entity.FundingEntity
import com.danbam.mobile.ui.funding.navigation.FundingDeepLinkKey
import com.danbam.mobile.ui.funding.navigation.FundingNavigationItem
import com.danbam.mobile.ui.movie.navigation.MovieNavigationItem

@Composable
fun ResultSearchScreen(
    navController: NavController,
    resultSearchViewModel: ResultSearchViewModel = hiltViewModel(),
    onClickAction: (() -> Unit),
    keyword: String,
) {
    var currentTab: SearchTab by remember { mutableStateOf(SearchTab.Movie) }

    LaunchedEffect(Unit) {
        resultSearchViewModel.search(keyword = keyword)
    }

    IndiStrawColumnBackground(
        onClickAction = onClickAction
    ) {
        Row(
            modifier = Modifier.padding(start = 15.dp, top = 22.dp)
        ) {
            IndiStrawTab(
                text = stringResource(id = R.string.indi_movie),
                isSelect = currentTab == SearchTab.Movie
            ) {
                currentTab = SearchTab.Movie
            }
            Spacer(modifier = Modifier.width(16.dp))
            IndiStrawTab(
                text = stringResource(id = R.string.crowd_funding),
                isSelect = currentTab == SearchTab.Funding
            ) {
                currentTab = SearchTab.Funding
            }
        }
        when (currentTab) {
            is SearchTab.Movie -> {
                Spacer(modifier = Modifier.height(11.dp))
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(20) {
                        MovieItem {
                            navController.navigate(MovieNavigationItem.Detail.route)
                        }
                    }
                }
            }

            is SearchTab.Funding -> {
                Spacer(modifier = Modifier.height(11.dp))
                RemoveOverScrollLazyColumn {
                    items(20) {
                        FundingItem(
                            item = FundingEntity(
                                0,
                                "존윅",
                                "진짜 재밌음",
                                50.0,
                                "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                                ""
                            )
                        ) {
                            navController.navigate(FundingNavigationItem.Detail.route + FundingDeepLinkKey.FUNDING_INDEX + it)
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}