package com.danbam.indistraw.mobile.ui.search.searching

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.danbam.indistraw.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.design_system.component.ExampleTextMedium
import com.danbam.indistraw.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.indistraw.design_system.util.indiStrawClickable

@Composable
fun SearchingScreen(
    searchingViewModel: SearchingViewModel = hiltViewModel(),
    keyword: String,
    onClickAction: (() -> Unit),
    onSearch: (String) -> Unit,
) {
    val container = searchingViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    LaunchedEffect(keyword) {
        searchingViewModel.getRelatedSearch(keyword = keyword)
    }

    IndiStrawColumnBackground(
        onClickAction = onClickAction
    ) {
        Spacer(modifier = Modifier.height(37.dp))
        state.relatedSearchPager?.let {
            RemoveOverScrollLazyColumn {
                items(it) { item ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .indiStrawClickable {
                                onClickAction()
                                onSearch(item)
                            }
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
}