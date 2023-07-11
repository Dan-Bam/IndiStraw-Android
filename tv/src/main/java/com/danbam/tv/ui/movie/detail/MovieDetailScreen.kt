package com.danbam.tv.ui.movie.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.ButtonMedium
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.R
import com.danbam.design_system.component.JoinBold
import com.danbam.design_system.component.TitleRegular
import com.danbam.tv.ui.main.navigation.MainNavigationItem

sealed class MovieTabItem(val stringId: Int) {
    object Highlight : MovieTabItem(R.string.highlight)
    object Actor : MovieTabItem(R.string.actor)
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    navController: NavController
) {
    var currentTab: MovieTabItem by remember { mutableStateOf(MovieTabItem.Highlight) }
    IndiStrawTvBackground {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6F)
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxWidth(0.6F)
                    .fillMaxHeight(),
                model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                contentDescription = "movieThumbnail",
                contentScale = ContentScale.Crop,
                alpha = 0.7F
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .fillMaxHeight(0.9F)
                    .align(Alignment.TopStart)
                    .padding(start = 70.dp, top = 60.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    HeadLineBold(text = "범죄도시 2", fontSize = 50)
                    Spacer(modifier = Modifier.height(15.dp))
                    ExampleTextMedium(
                        text = "아, 이유가 어딨어, 사람 죽인 새끼 잡는데?! 나쁜 놈은 그냥 잡는거야! 도주 용의자 사건을 담당하러 필리핀으로 간 마석도와 전일만은 예상치 못한 사건에 휘말리게 된다.",
                        fontSize = 18,
                        maxLines = 3
                    )
                }
                Row {
                    MoviePlayButton(icon = IndiStrawIconList.FastPlay, title = "이어서 보기") {
                        navController.navigate(MainNavigationItem.MoviePlay.route)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    MoviePlayButton(icon = IndiStrawIconList.PlayFirst, title = "처음부터 보기") {
                        navController.navigate(MainNavigationItem.MoviePlay.route)
                    }
                }
            }
        }
        Row(
            modifier = Modifier.padding(start = 70.dp)
        ) {
            MovieTab(MovieTabItem.Highlight, currentTab) {
                currentTab = it
            }
            Spacer(modifier = Modifier.width(19.dp))
            MovieTab(MovieTabItem.Actor, currentTab) {
                currentTab = it
            }
        }
        Divider(
            modifier = Modifier
                .padding(start = 70.dp, top = 10.dp, bottom = 15.dp)
                .height(2.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray)
        )
        when (currentTab) {
            is MovieTabItem.Highlight -> {
                TvLazyRow(
                    modifier = Modifier.padding(start = 70.dp),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp)
                ) {
                    items(10) {
                        Surface(onClick = { }) {
                            AsyncImage(
                                modifier = Modifier
                                    .width(210.dp)
                                    .height(150.dp)
                                    .clip(IndiStrawTheme.shapes.smallRounded),
                                model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                                contentDescription = "highlightImage",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            is MovieTabItem.Actor -> {
                TvLazyRow(
                    modifier = Modifier.padding(start = 70.dp),
                    horizontalArrangement = Arrangement.spacedBy(35.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp)
                ) {
                    items(10) {
                        Surface(
                            shape = ClickableSurfaceDefaults.shape(
                                shape = RoundedCornerShape(0.dp)
                            ),
                            color = ClickableSurfaceDefaults.color(
                                color = Color.Transparent,
                                focusedColor = Color.Transparent,
                                pressedColor = Color.Transparent,
                                disabledColor = Color.Transparent
                            ),
                            onClick = { }
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(150.dp),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(IndiStrawTheme.shapes.circle),
                                    model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                                    contentDescription = "actorProfile",
                                    contentScale = ContentScale.Crop
                                )
                                HeadLineBold(text = "마동석")
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MoviePlayButton(
    icon: IndiStrawIconList,
    title: String,
    onClick: () -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.onFocusChanged {
            focused = it.hasFocus || it.isFocused
        },
        shape = ClickableSurfaceDefaults.shape(
            shape = IndiStrawTheme.shapes.defaultRounded
        ),
        scale = ClickableSurfaceDefaults.scale(
            focusedScale = 1F
        ),
        color = ClickableSurfaceDefaults.color(
            color = IndiStrawTheme.colors.darkGray3,
            focusedColor = IndiStrawTheme.colors.main,
            pressedColor = IndiStrawTheme.colors.main,
            disabledColor = IndiStrawTheme.colors.darkGray3
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 9.dp)
                .background(
                    if (focused) IndiStrawTheme.colors.main else IndiStrawTheme.colors.darkGray3,
                    IndiStrawTheme.shapes.defaultRounded
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IndiStrawIcon(modifier = Modifier.size(20.dp), icon = icon)
            Spacer(modifier = Modifier.width(10.dp))
            ButtonMedium(text = title)
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MovieTab(
    tab: MovieTabItem,
    currentTab: MovieTabItem,
    onClick: (MovieTabItem) -> Unit
) {
    Surface(
        shape = ClickableSurfaceDefaults.shape(
            shape = RoundedCornerShape(0.dp)
        ),
        color = ClickableSurfaceDefaults.color(
            color = Color.Transparent,
            focusedColor = Color.Transparent,
            pressedColor = Color.Transparent,
            disabledColor = Color.Transparent
        ),
        onClick = { onClick(tab) }
    ) {
        if (currentTab == tab) {
            HeadLineBold(text = stringResource(id = tab.stringId))
        } else {
            TitleRegular(
                text = stringResource(id = tab.stringId),
                fontSize = 24,
                color = IndiStrawTheme.colors.gray
            )
        }
    }
}