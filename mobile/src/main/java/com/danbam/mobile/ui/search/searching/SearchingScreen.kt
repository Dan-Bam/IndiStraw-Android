package com.danbam.mobile.ui.search.searching

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.FindPasswordMedium
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.design_system.util.indiStrawClickable

@Composable
fun SearchingScreen(
    onSearch: (String) -> Unit,
) {
    IndiStrawColumnBackground {
        Spacer(modifier = Modifier.height(37.dp))
        RemoveOverScrollLazyColumn {
            itemsIndexed(
                listOf(
                    "슬램덩크",
                    "슬램덩크",
                    "슬램덩크",
                    "슬램덩크",
                    "슬램덩크",
                    "슬램덩크",
                    "슬램덩크",
                    "슬램덩크",
                    "슬램덩크",
                    "슬램덩크"
                )
            ) { _, item ->
                Row(
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .indiStrawClickable { onSearch(item) }
                ) {
                    IndiStrawIcon(icon = IndiStrawIconList.Search)
                    Spacer(modifier = Modifier.width(14.dp))
                    ExampleTextMedium(text = item)
                    Spacer(modifier = Modifier.weight(1F))
                    IndiStrawIcon(icon = IndiStrawIconList.FastSearch)
                }
                Spacer(modifier = Modifier.height(44.dp))
            }
        }
    }
}