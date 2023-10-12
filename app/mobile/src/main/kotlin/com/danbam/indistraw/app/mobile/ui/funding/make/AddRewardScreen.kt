package com.danbam.indistraw.app.mobile.ui.funding.make

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.HeadLineBold
import com.danbam.indistraw.core.design_system.component.RewardItem
import com.danbam.indistraw.core.design_system.component.RewardType
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.util.androidx.RemoveOverScrollLazyColumn
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.entity.funding.FundingDetailEntity

@Composable
fun AddRewardScreen(
    makeFundingViewModel: MakeFundingViewModel,
    onAdd: () -> Unit,
    onNext: () -> Unit
) {
    val container = makeFundingViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    Spacer(modifier = Modifier.padding(top = 20.dp))
    IndiStrawColumnBackground {
        HeadLineBold(
            modifier = Modifier.padding(start = 32.dp, bottom = 28.dp),
            text = stringResource(id = R.string.add_reward)
        )
        RemoveOverScrollLazyColumn(
            modifier = Modifier.weight(1F),
            verticalArrangement = if (state.rewardList.isEmpty()) Arrangement.Center else Arrangement.Top
        ) {
            itemsIndexed(state.rewardList) { index, item ->
                RewardItem(
                    rewardType = RewardType.Expand,
                    item = FundingDetailEntity.RewardEntity(
                        idx = 0L,
                        title = item.title,
                        description = item.description,
                        price = item.price,
                        totalCount = item.totalCount,
                        imageList = item.imageList
                    ),
                    onDelete = {
                        makeFundingViewModel.removeReward(index)
                    }
                )
                Spacer(modifier = Modifier.height(23.dp))
            }
            item(1) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .border(1.dp, IndiStrawTheme.colors.white, IndiStrawTheme.shapes.circle)
                            .padding(10.dp)
                            .indiStrawClickable(onClick = onAdd)
                    ) {
                        IndiStrawIcon(icon = IndiStrawIconList.Plus)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    TitleRegular(text = stringResource(id = R.string.do_add_reward))
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        IndiStrawButton(text = stringResource(id = R.string.next)) {
            onNext()
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}