package com.danbam.mobile.ui.search.start_search

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.R
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.IndiStrawChipList
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.design_system.util.indiStrawClickable

@Composable
fun StartSearchScreen(
    onSearch: (String) -> Unit,
) {
    IndiStrawColumnBackground {
        TitleSemiBold(
            modifier = Modifier.padding(start = 25.dp, top = 24.dp),
            text = stringResource(id = R.string.popular_search),
            color = IndiStrawTheme.colors.gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        IndiStrawChipList(itemList = listOf("최신영화", "신규영화"), onItemSelect = onSearch)
        TitleSemiBold(
            modifier = Modifier.padding(start = 25.dp, 36.dp),
            text = stringResource(id = R.string.recent_search),
            color = IndiStrawTheme.colors.gray
        )
        Spacer(modifier = Modifier.height(16.dp))
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
                ExampleTextMedium(
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .indiStrawClickable { onSearch(item) },
                    text = item
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}