package com.danbam.mobile.ui.funding.make

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.util.indiStrawClickable
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun AddRewardScreen(
    onAdd: () -> Unit,
    onNext: () -> Unit
) {
    val itemList = remember { mutableStateListOf<Int>() }
    val scrollState = rememberScrollState()
    Spacer(modifier = Modifier.padding(top = 20.dp))
    IndiStrawColumnBackground {
        HeadLineBold(
            modifier = Modifier.padding(start = 32.dp, bottom = 28.dp),
            text = stringResource(id = R.string.add_reward)
        )
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1F)
        ) {
            repeat(itemList.size) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(IndiStrawTheme.colors.gray)
                )
                Spacer(modifier = Modifier.height(23.dp))
            }
            if (itemList.size == 0) Spacer(modifier = Modifier.weight(1F))
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
            Spacer(modifier = Modifier.weight(1F))
        }
        Spacer(modifier = Modifier.height(20.dp))
        IndiStrawButton(text = stringResource(id = R.string.next)) {
            onNext()
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}