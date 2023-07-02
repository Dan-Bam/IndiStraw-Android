package com.danbam.mobile.ui.funding.all

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.component.FundingItem
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.domain.entity.FundingEntity
import com.danbam.mobile.ui.funding.navigation.FundingDeepLinkKey
import com.danbam.mobile.ui.funding.navigation.FundingNavigationItem

@Composable
fun FundingAllScreen(
    navController: NavController
) {
    IndiStrawColumnBackground {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(12.dp))
        RemoveOverScrollLazyColumn {
            items(10) {
                FundingItem(
                    item = FundingEntity(0, "adasdsd", "adadada", 50, "", ""),
                    onClickItem = { navController.navigate(FundingNavigationItem.FundingDetail.route + FundingDeepLinkKey.FUNDING_INDEX + it) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}