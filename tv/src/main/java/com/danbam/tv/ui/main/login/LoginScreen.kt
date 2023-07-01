package com.danbam.tv.ui.main.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.R
import com.danbam.design_system.component.ExampleTextRegular
import com.danbam.design_system.component.IndiStrawTvBackground
import com.danbam.design_system.component.IndiStrawTvButton
import com.danbam.design_system.component.IndiStrawTvTextField

@Composable
fun LoginScreen(
    navController: NavController
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    IndiStrawTvBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1F))
            HeadLineBold(text = stringResource(id = R.string.app_name), fontSize = 57)
            Spacer(modifier = Modifier.fillMaxHeight(0.05F))
            IndiStrawTvTextField(
                hint = stringResource(id = R.string.id),
                value = id,
                onValueChange = { id = it })
            Spacer(modifier = Modifier.fillMaxHeight(0.04F))
            IndiStrawTvTextField(
                hint = stringResource(id = R.string.password),
                value = password,
                onValueChange = { password = it })

            Spacer(modifier = Modifier.fillMaxHeight(0.09F))
            IndiStrawTvButton(text = stringResource(id = R.string.login)) {

            }
            Spacer(modifier = Modifier.fillMaxHeight(0.02F))
            ExampleTextRegular(text = "OR", fontSize = 24)
            Spacer(modifier = Modifier.fillMaxHeight(0.02F))
            IndiStrawTvButton(text = stringResource(id = R.string.qr_login)) {

            }
        }
    }
}