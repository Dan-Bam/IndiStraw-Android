package com.danbam.mobile.ui.movie.all

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.danbam.design_system.component.IndiStrawGenreList
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.MovieGenre
import com.danbam.design_system.component.MovieItem
import com.danbam.mobile.ui.movie.navigation.MovieNavigationItem

@Composable
fun MovieAllScreen(
    navController: NavController,
) {
    var currentGenre: MovieGenre by remember { mutableStateOf(MovieGenre.All) }
    IndiStrawColumnBackground {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        IndiStrawGenreList(
            itemList = MovieGenre.toList(),
            selectedItem = currentGenre,
            onItemSelect = {
                currentGenre = it
            })
        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 15.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            items(30) {
                MovieItem {
                    navController.navigate(MovieNavigationItem.Detail.route)
                }
            }
        }
    }
}