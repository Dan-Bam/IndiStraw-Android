package com.danbam.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import com.danbam.design_system.util.indiStrawClickable

sealed class MovieGenre(val stringId: Int) {
    companion object {
        fun toList() =
            listOf(All, Action, Romance, Comedy, Thriller, Drama, SF, Animation, Adventure, Fantasy)
    }

    object All : MovieGenre(R.string.genre_all)
    object Action : MovieGenre(R.string.genre_action)
    object Romance : MovieGenre(R.string.genre_romance)
    object Comedy : MovieGenre(R.string.genre_comedy)
    object Thriller : MovieGenre(R.string.genre_thriller)
    object Drama : MovieGenre(R.string.genre_drama)
    object SF : MovieGenre(R.string.genre_sf)
    object Animation : MovieGenre(R.string.genre_animation)
    object Adventure : MovieGenre(R.string.genre_adventure)
    object Fantasy : MovieGenre(R.string.genre_fantasy)
}

@Composable
fun IndiStrawChipList(
    itemList: List<String>,
    selectedItem: String? = null,
    onItemSelect: (String) -> Unit,
) {
    RemoveOverScrollLazyRow {
        item {
            Spacer(modifier = Modifier.width(15.dp))
        }
        itemsIndexed(itemList) { _, item ->
            IndiStrawChip(
                text = if (selectedItem == null) "#$item" else item,
                onSelect = onItemSelect,
                isSelect = if (selectedItem == null) true else item == selectedItem
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun IndiStrawGenreList(
    itemList: List<MovieGenre>,
    selectedItem: MovieGenre,
    onItemSelect: (MovieGenre) -> Unit,
) {
    RemoveOverScrollLazyRow {
        item {
            Spacer(modifier = Modifier.width(15.dp))
        }
        itemsIndexed(itemList) { _, item ->
            IndiStrawChip(
                item = item,
                onSelect = onItemSelect,
                isSelect = item == selectedItem
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
private fun IndiStrawChip(
    modifier: Modifier = Modifier,
    text: String,
    onSelect: (String) -> Unit,
    isSelect: Boolean,
) {
    TitleRegular(
        modifier = modifier
            .background(
                color = if (isSelect) IndiStrawTheme.colors.main else IndiStrawTheme.colors.darkGray2,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .indiStrawClickable(onClick = { onSelect(text) })
            .padding(horizontal = 5.dp, vertical = 4.dp),
        text = text,
        color = if (isSelect) IndiStrawTheme.colors.white else IndiStrawTheme.colors.lightGray
    )
}

@Composable
private fun IndiStrawChip(
    modifier: Modifier = Modifier,
    item: MovieGenre,
    onSelect: (MovieGenre) -> Unit,
    isSelect: Boolean,
) {
    TitleRegular(
        modifier = modifier
            .background(
                color = if (isSelect) IndiStrawTheme.colors.main else IndiStrawTheme.colors.darkGray2,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .indiStrawClickable(onClick = { onSelect(item) })
            .padding(horizontal = 5.dp, vertical = 4.dp),
        text = stringResource(id = item.stringId),
        color = if (isSelect) IndiStrawTheme.colors.white else IndiStrawTheme.colors.lightGray
    )
}