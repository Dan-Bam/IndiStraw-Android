package com.danbam.mobile.ui.movie.all

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.component.IndiStrawChipList
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.MovieItem
import com.danbam.design_system.component.MovieType
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
                MovieItem(movieType = MovieType.Detail) {
                    navController.navigate(MovieNavigationItem.MovieDetail.route)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}