package com.danbam.mobile.ui.funding.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.R
import com.danbam.design_system.component.AddImageList
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.component.IndiStrawToggle
import com.danbam.design_system.component.TitleRegular
import com.danbam.mobile.util.parser.toFile
import okhttp3.internal.toLongOrDefault

@Composable
fun WriteRewardScreen(
    makeFundingViewModel: MakeFundingViewModel,
    onAdd: () -> Unit
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var isReal by remember { mutableStateOf(false) }
    val imageList = remember { mutableStateListOf<String>() }
    var amount by remember { mutableStateOf("") }
    Spacer(modifier = Modifier.height(34.dp))
    IndiStrawColumnBackground(
        scrollEnabled = true
    ) {
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, bottom = 16.dp),
            text = stringResource(id = R.string.title)
        )
        IndiStrawTextField(
            hint = stringResource(id = R.string.require_title),
            value = title,
            onValueChange = { title = it })
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.introduce)
        )
        IndiStrawTextField(
            hint = stringResource(id = R.string.require_introduce),
            value = description,
            onValueChange = { description = it })
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.money)
        )
        IndiStrawTextField(
            hint = stringResource(id = R.string.require_money),
            value = price,
            onValueChange = { price = it })
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.is_real)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.darkGray, IndiStrawTheme.shapes.defaultRounded)
                .padding(horizontal = 13.dp, 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExampleTextMedium(text = stringResource(id = R.string.is_real_reward))
            IndiStrawToggle(isChecked = isReal, onChecked = { isReal = it })
        }
        if (isReal) {
            TitleRegular(
                modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 16.dp),
                text = stringResource(id = R.string.amount)
            )
            IndiStrawTextField(
                hint = stringResource(id = R.string.require_amount),
                value = amount,
                onValueChange = { amount = it })
        }
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp),
            text = stringResource(id = R.string.highlight)
        )
        Spacer(modifier = Modifier.height(16.dp))
        AddImageList(
            modifier = Modifier.padding(start = 15.dp),
            imageList = imageList,
            onRemove = { imageList.removeAt(it) }) {
            it?.let {
                makeFundingViewModel.uploadImage(it.toFile(context)) {
                    imageList.add(it)
                }
            }
        }
        Spacer(modifier = Modifier.height(37.dp))
        IndiStrawButton(text = stringResource(id = R.string.add)) {
            makeFundingViewModel.addReward(
                imageList = imageList,
                title = title,
                description = description,
                price = price.toLongOrDefault(0L),
                isReal = isReal,
                amount = amount.toLongOrNull(),
                onAdded = onAdd
            )
            onAdd()
        }
        Spacer(modifier = Modifier.height(93.dp))
    }
}