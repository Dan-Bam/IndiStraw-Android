package com.danbam.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

const val BannerTime = 3_000L

@OptIn(ExperimentalPagerApi::class)
@Composable
fun IndiStrawBanner(
    modifier: Modifier = Modifier,
    itemCount: Int,
    content: @Composable PagerScope.(page: Int) -> Unit,
) {
    val state = rememberPagerState()
    val bannerHeight = LocalConfiguration.current.screenHeightDp * 0.26

    LaunchedEffect(state.currentPage) {
        delay(BannerTime)
        withContext(NonCancellable) {
            state.animateScrollToPage(page = if (state.currentPage < itemCount - 1) state.currentPage + 1 else 0)
        }
    }

    Column(
        modifier = modifier
            .padding(start = 15.dp, end = 15.dp, top = 21.dp)
            .fillMaxWidth()
    ) {
        HorizontalPager(
            modifier = Modifier
                .height(bannerHeight.dp),
            count = itemCount,
            state = state
        ) {
            content(it)
        }
        Spacer(modifier = Modifier.height(16.dp))
        RemoveOverScrollLazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(itemCount) {
                Box {
                    if (it == state.currentPage) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .width(9.dp)
                                .height(9.dp)
                                .background(
                                    color = IndiStrawTheme.colors.white,
                                    shape = IndiStrawTheme.shapes.circle
                                )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 5.dp)
                            .width(5.dp)
                            .height(5.dp)
                            .background(
                                color = if (it == state.currentPage) IndiStrawTheme.colors.main else IndiStrawTheme.colors.white,
                                shape = IndiStrawTheme.shapes.circle
                            )
                    )
                }
            }
        }
    }
}