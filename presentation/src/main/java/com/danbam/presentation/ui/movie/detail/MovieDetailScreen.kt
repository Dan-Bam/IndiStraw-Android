package com.danbam.presentation.ui.movie.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.ButtonMedium
import com.danbam.design_system.component.FindPasswordMedium
import com.danbam.design_system.component.ImageButton
import com.danbam.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.Shape
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.design_system.R
import com.danbam.presentation.ui.movie.navigation.MovieNavigationItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailScreen(
    navController: NavController,
) {
    val movieHeight = LocalConfiguration.current.screenHeightDp * 0.3
    IndiStrawBottomSheetLayout(sheetContent = {
        Column(
            modifier = Modifier.align(CenterHorizontally),
            horizontalAlignment = CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(top = 31.dp)
                    .size(70.dp)
                    .clip(IndiStrawTheme.shapes.circle),
                model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                contentDescription = "Image",
                contentScale = ContentScale.Crop
            )
            FindPasswordMedium(modifier = Modifier.padding(top = 4.dp), text = "이동욱", fontSize = 14)
        }
        ButtonMedium(
            modifier = Modifier.padding(start = 20.dp, top = 29.dp), text = stringResource(
                id = R.string.participate_movie
            )
        )
        RemoveOverScrollLazyRow(
            modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.width(20.dp))
            }
            items(10) {
                ImageButton(
                    modifier = Modifier
                        .width(100.dp)
                        .height(90.dp),
                    imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    shape = Shape.Rectangle
                ) {

                }
                Spacer(modifier = Modifier.width(16.dp))
            }
            item {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }) { _, moreInfo ->
        IndiStrawColumnBackground(
            scrollEnabled = true
        ) {
            IndiStrawHeader(
                pressBackBtn = { navController.popBackStack() }
            )
            Box {
                ImageButton(
                    modifier = Modifier
                        .padding(top = 17.dp)
                        .height(movieHeight.dp)
                        .fillMaxWidth(),
                    imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    shape = Shape.None
                ) {
                    navController.navigate(MovieNavigationItem.MoviePlay.route)
                }
                IndiStrawIcon(
                    modifier = Modifier.align(Alignment.Center),
                    icon = IndiStrawIconList.Play
                )
            }
            TitleSemiBold(
                modifier = Modifier.padding(start = 25.dp, top = 15.dp, bottom = 8.dp),
                text = "스파이더맨",
                fontSize = 18
            )
            FindPasswordMedium(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth(),
                text = "스파이더맨은 스티브 딧코가 창작한 마블 코믹스의 슈퍼 히어로이다. 그는 1962년 8월의 어메이징 판타지15호에 처음 등장했다.그는 마블 코믹스에서 출판한 만화책뿐만 아니라 마블 유니버스를 배경으로 한 여러영화, tv프로이다.",
                color = IndiStrawTheme.colors.gray
            )
            TitleRegular(
                modifier = Modifier.padding(start = 25.dp, top = 44.dp), text = stringResource(
                    id = R.string.highlight
                )
            )
            RemoveOverScrollLazyRow(
                modifier = Modifier.padding(top = 14.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(25.dp))
                }
                items(10) {
                    AsyncImage(
                        modifier = Modifier
                            .height(100.dp)
                            .width(160.dp)
                            .clip(IndiStrawTheme.shapes.smallRounded),
                        model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            ButtonMedium(
                modifier = Modifier.padding(top = 60.dp, start = 25.dp, bottom = 10.dp),
                text = stringResource(id = R.string.actor)
            )
            RemoveOverScrollLazyRow {
                item {
                    Spacer(modifier = Modifier.width(25.dp))
                }
                items(10) {
                    Column(
                        modifier = Modifier.indiStrawClickable(onClick = moreInfo),
                        horizontalAlignment = CenterHorizontally
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(65.dp)
                                .clip(IndiStrawTheme.shapes.circle),
                            model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop
                        )
                        FindPasswordMedium(modifier = Modifier.padding(top = 6.dp), text = "이동욱")
                        TitleRegular(
                            modifier = Modifier.padding(top = 4.dp),
                            text = "감독",
                            color = IndiStrawTheme.colors.gray,
                            fontSize = 12
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}