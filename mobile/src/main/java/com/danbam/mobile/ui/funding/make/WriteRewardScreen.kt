package com.danbam.mobile.ui.funding.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.component.TitleRegular

@Composable
fun WriteRewardScreen(
    onAdd: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var introduce by remember { mutableStateOf("") }
    IndiStrawColumnBackground {
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, top = 36.dp, bottom = 16.dp),
            text = stringResource(id = R.string.thumbnail)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .background(
                    color = IndiStrawTheme.colors.gray,
                    shape = IndiStrawTheme.shapes.bigRounded
                )
                .padding(top = 36.dp, bottom = 29.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TitleRegular(
                text = stringResource(id = R.string.require_thumbnail),
                color = IndiStrawTheme.colors.black,
                fontSize = 14
            )
            Spacer(modifier = Modifier.height(29.dp))
            Row(
                modifier = Modifier
                    .background(
                        color = IndiStrawTheme.colors.main,
                        shape = IndiStrawTheme.shapes.bigRounded
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                IndiStrawIcon(icon = IndiStrawIconList.Plus)
                Spacer(modifier = Modifier.width(6.dp))
                TitleRegular(text = stringResource(id = R.string.upload_image), fontSize = 14)
            }
        }
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.title)
        )
        IndiStrawTextField(
            hint = stringResource(id = R.string.require_title),
            value = title,
            onValueChange = { title = it })
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.introduce)
        )
        IndiStrawTextField(
            hint = stringResource(id = R.string.require_introduce),
            value = introduce,
            onValueChange = { introduce = it })
        IndiStrawButton(text = stringResource(id = R.string.add)) {
            onAdd()
        }
    }
}