package com.danbam.indistraw.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.design_system.IndiStrawTheme
import com.danbam.indistraw.design_system.R
import com.danbam.indistraw.design_system.util.RemoveOverScrollLazyRow
import com.danbam.indistraw.design_system.util.indiStrawClickable

sealed class MovieGenre(val stringId: Int, val genre: String?) {
    companion object {
        fun toList() =
            listOf(All, Action, Romance, Comedy, Thriller, Drama, SF, Animation, Adventure, Fantasy)
    }

    object All : MovieGenre(R.string.genre_all, null)
    object Action : MovieGenre(R.string.genre_action, "액션")
    object Romance : MovieGenre(R.string.genre_romance, "로맨스")
    object Comedy : MovieGenre(R.string.genre_comedy, "코미디")
    object Thriller : MovieGenre(R.string.genre_thriller, "스릴러")
    object Drama : MovieGenre(R.string.genre_drama, "드라마")
    object SF : MovieGenre(R.string.genre_sf, "SF")
    object Animation : MovieGenre(R.string.genre_animation, "애니메이션")
    object Adventure : MovieGenre(R.string.genre_adventure, "모험")
    object Fantasy : MovieGenre(R.string.genre_fantasy, "판타지")
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