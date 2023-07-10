package com.danbam.tv.ui.movie.movie

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.component.MovieTvItem
import com.danbam.design_system.component.TitleRegular

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieScreen(

) {
    val tabWidth = LocalConfiguration.current.screenWidthDp * 0.13
    IndiStrawTvBackground {
        TvLazyRow(
            modifier = Modifier.padding(top = 90.dp, end = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(10) {
                Surface(
                    scale = ClickableSurfaceDefaults.scale(
                        focusedScale = 1F
                    ),
                    shape = ClickableSurfaceDefaults.shape(
                        shape = IndiStrawTheme.shapes.smallRounded
                    ),
                    border = ClickableSurfaceDefaults.border(
                        border = Border(
                            border = BorderStroke(1.dp, IndiStrawTheme.colors.gray),
                            shape = IndiStrawTheme.shapes.smallRounded
                        ),
                        focusedBorder = Border.None
                    ),
                    color = ClickableSurfaceDefaults.color(
                        color = IndiStrawTheme.colors.navy,
                        focusedColor = IndiStrawTheme.colors.main,
                        pressedColor = IndiStrawTheme.colors.main,
                        disabledColor = IndiStrawTheme.colors.navy
                    ),
                    onClick = { }
                ) {
                    TitleRegular(
                        modifier = Modifier
                            .width(tabWidth.dp)
                            .padding(vertical = 10.dp),
                        text = "전체",
                        fontSize = 24,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        TvLazyVerticalGrid(
            modifier = Modifier.padding(top = 20.dp, end = 40.dp),
            columns = TvGridCells.Fixed(6),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            items(100) {
                MovieTvItem {

                }
            }
        }
    }
}