package com.danbam.mobile.ui.movie.all

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.component.ImageButton
import com.danbam.design_system.component.IndiStrawChipList
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.Shape
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.mobile.ui.movie.navigation.MovieNavigationItem

@Composable
fun MovieAllScreen(
    navController: NavController,
) {
    var currentGenre by remember { mutableStateOf("전체") }
    IndiStrawColumnBackground {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        IndiStrawChipList(
            itemList = listOf("전체", "스릴러", "로맨스", "코미디"),
            selectedItem = currentGenre,
            onItemSelect = {
                currentGenre = it
            })
        Spacer(modifier = Modifier.height(20.dp))
        RemoveOverScrollLazyColumn {
            items(30) {
                Row(
                    modifier = Modifier.padding(horizontal = 15.dp)
                ) {
                    ImageButton(
                        modifier = Modifier
                            .weight(1F)
                            .height(160.dp),
                        imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                        shape = Shape.Rectangle
                    ) {
                        navController.navigate(MovieNavigationItem.MovieDetail.route)
                    }
                    Spacer(modifier = Modifier.width(9.dp))
                    ImageButton(
                        modifier = Modifier
                            .weight(1F)
                            .height(160.dp),
                        imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                        shape = Shape.Rectangle
                    ) {
                        navController.navigate(MovieNavigationItem.MovieDetail.route)
                    }
                    Spacer(modifier = Modifier.width(9.dp))
                    ImageButton(
                        modifier = Modifier
                            .weight(1F)
                            .height(160.dp),
                        imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                        shape = Shape.Rectangle
                    ) {
                        navController.navigate(MovieNavigationItem.MovieDetail.route)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}