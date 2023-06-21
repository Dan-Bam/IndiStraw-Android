package com.danbam.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import com.danbam.design_system.util.indiStrawClickable

@Composable
fun IndiStrawChipList(
    itemList: List<String>,
    selectedItem: String? = null,
    onItemSelect: (String) -> Unit,
) {
    RemoveOverScrollLazyRow {
        item {
            Spacer(modifier = Modifier.width(if (selectedItem == null) 25.dp else 15.dp))
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