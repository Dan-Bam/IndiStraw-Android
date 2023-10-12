package com.danbam.indistraw.app.mobile.ui.funding.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.danbam.indistraw.core.design_system.component.ButtonMedium
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.FindPasswordMedium
import com.danbam.indistraw.core.design_system.component.HeadLineBold
import com.danbam.indistraw.core.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.indistraw.core.design_system.component.IndiStrawTextField
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable

sealed class Bank(val stringId: Int, val BankIcon: IndiStrawIconList, val name: String) {
    object NhBank : Bank(R.string.nh_bank, IndiStrawIconList.BankNh, "NH")
    object KakaoBank : Bank(R.string.kakao_bank, IndiStrawIconList.BankKakao, "KAKAO")
    object TossBank : Bank(R.string.toss_bank, IndiStrawIconList.BankToss, "TOSS")
    object ShinhanBank : Bank(R.string.shinhan_bank, IndiStrawIconList.BankShinhan, "SHINHAN")
    object WooriBank : Bank(R.string.woori_bank, IndiStrawIconList.BankWoori, "WOORI")
    object PostBank : Bank(R.string.post_bank, IndiStrawIconList.BankPost, "POSTOFFICE")
    object ShinhyeopBank :
        Bank(R.string.shinhyeop_bank, IndiStrawIconList.BankShinhyeop, "SHINHYEOP")

    object GwangjuBank : Bank(R.string.gwangju_bank, IndiStrawIconList.BankGwangju, "GWANGJU")
    object HanaBank : Bank(R.string.hana_bank, IndiStrawIconList.BankHana, "HANA")

    companion object {
        fun toList() = listOf(
            NhBank,
            KakaoBank,
            TossBank,
            ShinhanBank,
            WooriBank,
            PostBank,
            ShinhyeopBank,
            GwangjuBank,
            HanaBank
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WriteAccountScreen(
    makeFundingViewModel: MakeFundingViewModel,
    onFinish: () -> Unit
) {
    var accountNumber by remember { mutableStateOf("") }
    var selectBank: Bank by remember { mutableStateOf(Bank.NhBank) }
    var sheetAction: () -> Unit by remember { mutableStateOf({}) }
    IndiStrawBottomSheetLayout(sheetContent = {
        ButtonMedium(
            modifier = Modifier.padding(start = 15.dp, top = 20.dp),
            text = stringResource(id = R.string.require_bank)
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalGrid(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(Bank.toList()) {
                Column(
                    modifier = Modifier
                        .background(
                            IndiStrawTheme.colors.darkGray,
                            IndiStrawTheme.shapes.defaultRounded
                        )
                        .padding(top = 12.dp, bottom = 9.dp)
                        .indiStrawClickable {
                            selectBank = it
                            sheetAction()
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IndiStrawIcon(modifier = Modifier.height(30.dp), icon = it.BankIcon)
                    Spacer(modifier = Modifier.height(5.dp))
                    FindPasswordMedium(text = stringResource(id = it.stringId))
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }) { _, openSheet ->
        IndiStrawColumnBackground {
            HeadLineBold(
                modifier = Modifier.padding(start = 15.dp, top = 16.dp, bottom = 20.dp),
                text = stringResource(id = R.string.account_number)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .background(
                        color = IndiStrawTheme.colors.darkGray,
                        shape = IndiStrawTheme.shapes.defaultRounded
                    )
                    .padding(vertical = 12.dp, horizontal = 13.dp)
                    .indiStrawClickable {
                        sheetAction = openSheet
                        openSheet()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                IndiStrawIcon(modifier = Modifier.height(30.dp), icon = selectBank.BankIcon)
                Spacer(modifier = Modifier.width(8.dp))
                ExampleTextMedium(text = stringResource(id = selectBank.stringId))
                Spacer(modifier = Modifier.weight(1F))
                IndiStrawIcon(modifier = Modifier.width(12.dp), icon = IndiStrawIconList.Down)
            }
            Spacer(modifier = Modifier.height(28.dp))
            IndiStrawTextField(
                hint = stringResource(id = R.string.require_account_number),
                value = accountNumber,
                onValueChange = { accountNumber = it })
            Spacer(modifier = Modifier.height(36.dp))
            IndiStrawButton(text = stringResource(id = R.string.check)) {
                makeFundingViewModel.fundingCreate(selectBank, accountNumber) {
                    onFinish()
                }
            }
        }
    }
}