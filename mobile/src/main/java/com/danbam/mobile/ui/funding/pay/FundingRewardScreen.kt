package com.danbam.mobile.ui.funding.pay

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.util.toCommaString
import com.danbam.design_system.R
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.util.findActivity
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.mobile.util.android.getActivity
import com.danbam.mobile.util.android.observeWithLifecycle
import com.danbam.mobile.util.pay.bootPayCreate
import com.danbam.mobile.util.pay.bootPayPayload
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.UUID

sealed class Payment(val stringId: Int, val method: String) {
    companion object {
        fun toList() = listOf(Naver, Card, Account, Kakao)
    }

    object Naver : Payment(R.string.payment_naver, "naverpay")
    object Card : Payment(R.string.payment_card, "card")
    object Account : Payment(R.string.payment_account, "bank")
    object Kakao : Payment(R.string.payment_kakao, "kakaopay")

}

@OptIn(InternalCoroutinesApi::class)
@Composable
fun FundingRewardScreen(
    navController: NavController,
    fundingIdx: Long,
    rewardIndex: Long,
    rewardTitle: String,
    rewardDescription: String,
    rewardPrice: Long,
    fundingRewardViewModel: FundingRewardViewModel = hiltViewModel()
) {
    val container = fundingRewardViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var selectedPayment: Payment by remember { mutableStateOf(Payment.Naver) }
    var addFundingMoney by remember { mutableStateOf(0L) }
    val context = LocalContext.current

    sideEffect.observeWithLifecycle {
        when (it) {
            is FundingRewardSideEffect.SuccessFunding -> {
                navController.popBackStack()
            }
        }
    }

    LaunchedEffect(Unit) {
        fundingRewardViewModel.getProfile()
        fundingRewardViewModel.getReceipt()
    }

    IndiStrawColumnBackground(
        scrollEnabled = true
    ) {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
        Divider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.lightBlack)
        )
        TitleSemiBold(
            modifier = Modifier.padding(horizontal = 15.dp),
            text = stringResource(id = R.string.delivery_info),
            fontSize = 18
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            TitleRegular(
                modifier = Modifier.padding(start = 15.dp),
                text = state.name,
                fontSize = 14
            )
            Spacer(modifier = Modifier.width(8.dp))
            TitleRegular(text = "(${state.phoneNumber})", fontSize = 14)
        }
        Spacer(modifier = Modifier.height(8.dp))
        TitleRegular(
            modifier = Modifier
                .padding(horizontal = 15.dp),
            text = state.address ?: stringResource(id = R.string.no_address),
            color = IndiStrawTheme.colors.lightGray,
            fontSize = 14
        )
        state.zipCode?.let {
            TitleRegular(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = "(${state.zipCode})",
                color = IndiStrawTheme.colors.lightGray,
                fontSize = 14
            )
        }
        Divider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.lightBlack)
        )
        TitleSemiBold(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.reward_info),
            fontSize = 18
        )
        Spacer(modifier = Modifier.height(12.dp))
        TitleRegular(
            modifier = Modifier.padding(horizontal = 15.dp),
            text = rewardTitle,
            fontSize = 14
        )
        Spacer(modifier = Modifier.height(8.dp))
        TitleRegular(
            modifier = Modifier.padding(horizontal = 15.dp),
            text = rewardDescription,
            fontSize = 14,
            color = IndiStrawTheme.colors.gray
        )
        Spacer(modifier = Modifier.height(17.dp))
        TitleSemiBold(
            modifier = Modifier.padding(horizontal = 15.dp),
            text = "${rewardPrice.toCommaString()}${stringResource(id = R.string.money_unit)}"
        )
        Divider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.lightBlack)
        )
        TitleSemiBold(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.add_funding),
            fontSize = 18
        )
        Spacer(modifier = Modifier.height(4.dp))
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.add_funding_description),
            fontSize = 14,
            color = IndiStrawTheme.colors.gray
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.padding(end = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.95F)
            ) {
                IndiStrawTextField(
                    hint = "",
                    value = if (addFundingMoney == 0L) "" else addFundingMoney.toString(),
                    onValueChange = { addFundingMoney = it.toLongOrNull() ?: 0 })
            }
            Spacer(modifier = Modifier.weight(1F))
            TitleSemiBold(text = stringResource(id = R.string.money_unit), fontSize = 18)
        }
        Divider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.lightBlack)
        )
        TitleSemiBold(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.payment_method),
            fontSize = 18
        )
        Spacer(modifier = Modifier.height(12.dp))
        PaymentList(selectedPayment = selectedPayment) {
            selectedPayment = it
        }
        Divider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.lightBlack)
        )
        TitleSemiBold(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.payment_detail),
            fontSize = 18
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
        ) {
            TitleRegular(
                text = stringResource(id = R.string.reward_price),
                fontSize = 14,
                color = IndiStrawTheme.colors.gray
            )
            Spacer(modifier = Modifier.weight(1F))
            TitleRegular(
                text = "${rewardPrice.toCommaString()}${stringResource(id = R.string.money_unit)}",
                fontSize = 14,
                color = IndiStrawTheme.colors.gray
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
        ) {
            TitleRegular(
                text = stringResource(id = R.string.add_funding),
                fontSize = 14,
                color = IndiStrawTheme.colors.gray
            )
            Spacer(modifier = Modifier.weight(1F))
            TitleRegular(
                text = "${addFundingMoney.toCommaString()}${stringResource(id = R.string.money_unit)}",
                fontSize = 14,
                color = IndiStrawTheme.colors.gray
            )
        }
        Divider(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.lightBlack)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
        ) {
            TitleSemiBold(
                text = stringResource(id = R.string.total_money),
                fontSize = 18,
            )
            Spacer(modifier = Modifier.weight(1F))
            TitleSemiBold(
                text = "${(rewardPrice + addFundingMoney).toCommaString()}${stringResource(id = R.string.money_unit)}",
                fontSize = 18,
            )
        }
        Divider(
            modifier = Modifier
                .padding(top = 23.dp, bottom = 37.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.lightBlack)
        )
        IndiStrawButton(text = stringResource(id = R.string.pay)) {
            context.findActivity()?.let {
                bootPayCreate(
                    activity = it,
                    applicationContext = it.applicationContext,
                    payload = bootPayPayload(
                        title = rewardTitle,
                        price = (rewardPrice + addFundingMoney).toDouble(),
                        orderId = state.receiptId,
                        method = selectedPayment.method
                    )
                ) {
                    fundingRewardViewModel.funding(
                        receiptId = it,
                        fundingIdx = fundingIdx,
                        rewardIdx = rewardIndex,
                        price = rewardPrice,
                        extraPrice = addFundingMoney
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(77.dp))
    }
}

@Composable
fun PaymentList(
    selectedPayment: Payment,
    onSelect: (Payment) -> Unit
) {
    val paymentList = Payment.toList()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(Payment.toList().size / 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                TitleRegular(
                    modifier = Modifier
                        .weight(1F)
                        .border(
                            BorderStroke(
                                if (selectedPayment == paymentList[it * 2]) 0.dp else 1.dp,
                                IndiStrawTheme.colors.lightGray
                            ),
                            IndiStrawTheme.shapes.smallRounded
                        )
                        .background(
                            if (selectedPayment == paymentList[it * 2]) IndiStrawTheme.colors.main else Color.Transparent,
                            IndiStrawTheme.shapes.smallRounded
                        )
                        .padding(10.dp)
                        .indiStrawClickable { onSelect(paymentList[it * 2]) },
                    text = stringResource(id = paymentList[it * 2].stringId),
                    textAlign = TextAlign.Center,
                    color = if (selectedPayment == paymentList[it * 2]) IndiStrawTheme.colors.white else IndiStrawTheme.colors.lightGray
                )
                TitleRegular(
                    modifier = Modifier
                        .weight(1F)
                        .border(
                            BorderStroke(
                                if (selectedPayment == paymentList[it * 2 + 1]) 0.dp else 1.dp,
                                IndiStrawTheme.colors.lightGray
                            ),
                            IndiStrawTheme.shapes.smallRounded
                        )
                        .background(
                            if (selectedPayment == paymentList[it * 2 + 1]) IndiStrawTheme.colors.main else Color.Transparent,
                            IndiStrawTheme.shapes.smallRounded
                        )
                        .padding(10.dp)
                        .indiStrawClickable { onSelect(paymentList[it * 2 + 1]) },
                    text = stringResource(id = paymentList[it * 2 + 1].stringId),
                    textAlign = TextAlign.Center,
                    color = if (selectedPayment == paymentList[it * 2 + 1]) IndiStrawTheme.colors.white else IndiStrawTheme.colors.lightGray
                )
            }
        }
    }
}