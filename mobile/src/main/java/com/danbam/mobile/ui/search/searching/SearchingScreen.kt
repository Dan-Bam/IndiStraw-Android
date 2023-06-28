package com.danbam.mobile.ui.search.searching

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.design_system.util.indiStrawClickable

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

    val relatedSearchPager = state.relatedSearchPager?.collectAsLazyPagingItems()

    LaunchedEffect(keyword) {
        searchingViewModel.getRelatedSearch(keyword = keyword)
    }

    IndiStrawColumnBackground(
        onClickAction = onClickAction
    ) {
        Spacer(modifier = Modifier.height(37.dp))
        relatedSearchPager?.let {
            when (it.loadState.refresh) {
                is LoadState.Loading -> {
                }

                is LoadState.Error -> {
                }

                else -> {
                    RemoveOverScrollLazyColumn {
                        items(it) { item ->
                            item?.let {
                                Row(
                                    modifier = Modifier
                                        .padding(horizontal = 25.dp)
                                        .indiStrawClickable {
                                            onClickAction()
                                            onSearch(item.title)
                                        }
                                ) {
                                    IndiStrawIcon(icon = IndiStrawIconList.Search)
                                    Spacer(modifier = Modifier.width(14.dp))
                                    ExampleTextMedium(text = item.title)
                                    Spacer(modifier = Modifier.weight(1F))
                                    IndiStrawIcon(icon = IndiStrawIconList.FastSearch)
                                }
                                Spacer(modifier = Modifier.height(44.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}