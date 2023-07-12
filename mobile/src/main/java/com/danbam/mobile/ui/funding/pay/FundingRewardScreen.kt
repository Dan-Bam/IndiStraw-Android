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
import com.danbam.design_system.util.indiStrawClickable

sealed class Payment(val stringId: Int) {
    companion object {
        fun toList() = listOf(Naver, App, Account, Kakao)
    }

    object Naver : Payment(R.string.id)
    object App : Payment(R.string.id)
    object Account : Payment(R.string.id)
    object Kakao : Payment(R.string.id)

}

@Composable
fun FundingRewardScreen(
    navController: NavController,
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

    LaunchedEffect(Unit) {
        fundingRewardViewModel.getProfile()
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
        TitleSemiBold(modifier = Modifier.padding(horizontal = 15.dp), text = "배송정보", fontSize = 18)
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            TitleRegular(modifier = Modifier.padding(start = 15.dp), text = state.name, fontSize = 14)
            Spacer(modifier = Modifier.width(8.dp))
            TitleRegular(text = "(${state.phoneNumber})", fontSize = 14)
        }
        Spacer(modifier = Modifier.height(8.dp))
        TitleRegular(
            modifier = Modifier
                .padding(horizontal = 15.dp),
            text = state.address ?: "주소가 존재하지 않습니다.",
            color = IndiStrawTheme.colors.lightGray,
            fontSize = 14
        )
        TitleRegular(
            modifier = Modifier.padding(horizontal = 15.dp),
            text = "(${state.zipCode})",
            color = IndiStrawTheme.colors.lightGray,
            fontSize = 14
        )
        Divider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.lightBlack)
        )
        TitleSemiBold(modifier = Modifier.padding(start = 15.dp), text = "상품정보", fontSize = 18)
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
            text = "${rewardPrice.toCommaString()}원"
        )
        Divider(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.lightBlack)
        )
        TitleSemiBold(modifier = Modifier.padding(start = 15.dp), text = "추가후원", fontSize = 18)
        Spacer(modifier = Modifier.height(4.dp))
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp),
            text = "추가금액을 입력하시면 추가로 후원이 됩니다.",
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
        TitleSemiBold(modifier = Modifier.padding(start = 15.dp), text = "결제방법", fontSize = 18)
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
        TitleSemiBold(modifier = Modifier.padding(start = 15.dp), text = "결제상세", fontSize = 18)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
        ) {
            TitleRegular(
                text = "상품가격",
                fontSize = 14,
                color = IndiStrawTheme.colors.gray
            )
            Spacer(modifier = Modifier.weight(1F))
            TitleRegular(
                text = "${rewardPrice.toCommaString()}원",
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
                text = "추가후원",
                fontSize = 14,
                color = IndiStrawTheme.colors.gray
            )
            Spacer(modifier = Modifier.weight(1F))
            TitleRegular(
                text = "${addFundingMoney.toCommaString()}원",
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
                text = "총액",
                fontSize = 18,
            )
            Spacer(modifier = Modifier.weight(1F))
            TitleSemiBold(
                text = "${(rewardPrice + addFundingMoney).toCommaString()}원",
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
        IndiStrawButton(text = "결제하기") {

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