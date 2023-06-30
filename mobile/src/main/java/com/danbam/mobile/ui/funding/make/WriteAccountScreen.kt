package com.danbam.mobile.ui.funding.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.R
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawTextField

@Composable
fun WriteAccountScreen(
    onFinish: () -> Unit
) {
    var accountNumber by remember { mutableStateOf("") }
    IndiStrawColumnBackground {
        HeadLineBold(
            modifier = Modifier.padding(start = 32.dp, top = 16.dp, bottom = 20.dp),
            text = stringResource(id = R.string.account_number)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .background(
                    color = IndiStrawTheme.colors.darkGray,
                    shape = IndiStrawTheme.shapes.defaultRounded
                )
                .padding(vertical = 12.dp, horizontal = 13.dp)
        ) {
            ExampleTextMedium(text = "카카오뱅크")
        }
        Spacer(modifier = Modifier.height(28.dp))
        IndiStrawTextField(
            hint = stringResource(id = R.string.require_account_number),
            value = accountNumber,
            onValueChange = { accountNumber = it })
        Spacer(modifier = Modifier.height(36.dp))
        IndiStrawButton(text = stringResource(id = R.string.check)) {
            onFinish()
        }
    }
}