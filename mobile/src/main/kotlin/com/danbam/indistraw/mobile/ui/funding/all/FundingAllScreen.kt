package com.danbam.indistraw.mobile.ui.funding.all

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.danbam.indistraw.core.design_system.component.FundingItem
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.util.androidx.RemoveOverScrollLazyColumn
import com.danbam.indistraw.mobile.ui.funding.navigation.FundingDeepLinkKey
import com.danbam.indistraw.mobile.ui.funding.navigation.FundingNavigationItem

@Composable
fun FundingAllScreen(
    fundingAllViewModel: FundingAllViewModel = hiltViewModel(),
    navController: NavController
) {
    val container = fundingAllViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val fundingList = state.fundingList?.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        fundingAllViewModel.fundingList()
    }

    IndiStrawColumnBackground {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(12.dp))
        fundingList?.let {
            when (it.loadState.refresh) {
                is LoadState.Loading -> {

                }

                is LoadState.Error -> {

                }

                else -> {
                    RemoveOverScrollLazyColumn {
                        items(it) {
                            it?.let {
                                FundingItem(
                                    item = it,
                                    onClickItem = { navController.navigate(FundingNavigationItem.Detail.route + FundingDeepLinkKey.FUNDING_INDEX + it) }
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}