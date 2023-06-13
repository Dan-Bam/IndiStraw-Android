package com.danbam.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.FundingChip
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTab
import com.danbam.design_system.component.IndiStrawTabRow
import com.danbam.design_system.component.MovieChip
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.R
import com.danbam.presentation.util.parser.toDp

@Composable
fun ProfileScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    var currentMovieChip: MovieChip by remember { mutableStateOf(MovieChip.RecentMovie) }
    var currentFundingChip: FundingChip by remember { mutableStateOf(FundingChip.ParticipantFunding) }
    var currentMovieIndex by remember { mutableStateOf(0) }
    var currentFundingIndex by remember { mutableStateOf(0) }
    val movieTabWidthList = remember { mutableStateListOf(0, 0) }
    val fundingTabWidthList = remember { mutableStateListOf(0, 0) }

    IndiStrawColumnBackground {
        IndiStrawHeader(
            backStringId = R.string.back,
            pressBackBtn = { navController.popBackStack() }) {
            Row {
                IndiStrawIcon(icon = IndiStrawIconList.Setting)
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
                            color = IndiStrawTheme.colors.exampleText,
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
            tabWidthList = movieTabWidthList,
            tabHeader = {
                IndiStrawTab(
                    modifier = Modifier.onSizeChanged {
                        movieTabWidthList[0] = it.width.toDp(context)
                    },
                    text = stringResource(id = R.string.recent_watch_movie),
                    isSelect = currentMovieChip == MovieChip.RecentMovie
                ) {
                    currentMovieIndex = 0
                    currentMovieChip = MovieChip.RecentMovie
                }
                Spacer(modifier = Modifier.width(12.dp))
                IndiStrawTab(
                    modifier = Modifier.onSizeChanged {
                        movieTabWidthList[1] = it.width.toDp(context)
                    },
                    text = stringResource(id = R.string.participate_movie),
                    isSelect = currentMovieChip == MovieChip.ParticipantMovie
                ) {
                    currentMovieIndex = 1
                    currentMovieChip = MovieChip.ParticipantMovie
                }
            }, moreData = { }, selectedIndex = currentMovieIndex
        )
        IndiStrawTabRow(
            modifier = Modifier
                .padding(top = 43.dp, start = 15.dp),
            itemList = listOf(""),
            tabWidthList = fundingTabWidthList,
            tabHeader = {
                IndiStrawTab(
                    modifier = Modifier.onSizeChanged {
                        fundingTabWidthList[0] = it.width.toDp(context)
                    },
                    text = stringResource(id = R.string.participate_funding),
                    isSelect = currentFundingChip == FundingChip.ParticipantFunding
                ) {
                    currentFundingIndex = 0
                    currentFundingChip = FundingChip.ParticipantFunding
                }
                Spacer(modifier = Modifier.width(12.dp))
                IndiStrawTab(
                    modifier = Modifier.onSizeChanged {
                        fundingTabWidthList[1] = it.width.toDp(context)
                    },
                    text = stringResource(id = R.string.my_funding),
                    isSelect = currentFundingChip == FundingChip.MyFunding
                ) {
                    currentFundingIndex = 1
                    currentFundingChip = FundingChip.MyFunding
                }
            }, moreData = { }, isCrowdFunding = true, selectedIndex = currentFundingIndex
        )
    }
}

@Preview
@Composable
fun preview() {
    val navController = rememberNavController()
    IndiStrawTheme {
        ProfileScreen(navController = navController)
    }
}