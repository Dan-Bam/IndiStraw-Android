package com.danbam.mobile.ui.funding.make

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.R
import com.danbam.design_system.component.TitleRegular

@Composable
fun WriteTargetScreen(
    onNext: () -> Unit
) {
    IndiStrawColumnBackground {
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, top = 36.dp, bottom = 16.dp),
            text = stringResource(id = R.string.target_money)
        )
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.end_date)
        )
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.file)
        )
        Spacer(modifier = Modifier.padding(top = 36.dp))
        IndiStrawButton(text = stringResource(id = R.string.next)) {
            onNext()
        }
    }
}