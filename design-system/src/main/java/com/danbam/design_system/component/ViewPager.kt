package com.danbam.design_system.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

const val BannerTime = 4_000L

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IndiStrawBanner(
    modifier: Modifier = Modifier,
    itemCount: Int,
    content: @Composable (page: Int) -> Unit,
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
            .padding(top = 21.dp)
            .fillMaxWidth()
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(bannerHeight.dp),
            pageCount = itemCount,
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
                                .size(9.dp)
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
                            .size(5.dp)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IndiStrawTvBanner(
    modifier: Modifier = Modifier,
    itemCount: Int,
    content: @Composable (page: Int) -> Unit,
) {
    val state = rememberPagerState()
    val bannerHeight = LocalConfiguration.current.screenHeightDp * 0.4

    LaunchedEffect(state.currentPage) {
        delay(BannerTime)
        withContext(NonCancellable) {
            state.animateScrollToPage(page = if (state.currentPage < itemCount - 1) state.currentPage + 1 else 0)
        }
    }

    Box(
        modifier = modifier
            .padding(top = 30.dp, end = 50.dp)
            .fillMaxWidth()
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(bannerHeight.dp),
            pageCount = itemCount,
            state = state
        ) {
            content(it)
        }
        Spacer(modifier = Modifier.height(16.dp))
        RemoveOverScrollLazyRow(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(itemCount) {
                Box {
                    if (it == state.currentPage) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(15.dp)
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
                            .size(10.dp)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IndiStrawSlider(
    modifier: Modifier = Modifier,
    itemCount: Int,
    content: @Composable (page: Int) -> Unit,
) {
    val state = rememberPagerState()
    val sliderHeight = LocalConfiguration.current.screenHeightDp * 0.22

    Column(
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(sliderHeight.dp),
            pageCount = itemCount,
            state = state
        ) {
            content(it)
        }
        Spacer(modifier = Modifier.height(8.dp))
        RemoveOverScrollLazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
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