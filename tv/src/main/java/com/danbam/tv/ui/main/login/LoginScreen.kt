package com.danbam.tv.ui.main.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.R
import com.danbam.design_system.component.ButtonMedium
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.IndiStrawRowBackground
import com.danbam.design_system.component.IndiStrawTvButton
import com.danbam.design_system.component.IndiStrawTvTextField
import com.danbam.design_system.util.indiStrawClickable

@Composable
fun LoginScreen(
    navController: NavController
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    IndiStrawRowBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.08F))
            HeadLineBold(text = stringResource(id = R.string.app_name), fontSize = 57)
            Spacer(modifier = Modifier.fillMaxHeight(0.08F))
            IndiStrawTvTextField(
                hint = stringResource(id = R.string.id),
                value = id,
                onValueChange = { id = it })
            Spacer(modifier = Modifier.fillMaxHeight(0.04F))
            IndiStrawTvTextField(
                hint = stringResource(id = R.string.password),
                value = password,
                onValueChange = { password = it })

            Spacer(modifier = Modifier.fillMaxHeight(0.08F))
            IndiStrawTvButton(text = stringResource(id = R.string.login)) {

            }
            Spacer(modifier = Modifier.fillMaxHeight(0.04F))
            ExampleTextMedium(text = "or", fontSize = 20)
            Spacer(modifier = Modifier.fillMaxHeight(0.04F))
            IndiStrawTvButton(text = "QR로 로그인") {

            }
        }
    }
}