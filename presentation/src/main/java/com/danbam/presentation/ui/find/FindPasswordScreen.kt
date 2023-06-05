package com.danbam.presentation.ui.find

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.presentation.R
import com.danbam.presentation.util.AppNavigationItem

@Composable
fun FindPasswordScreen(
    navController: NavController,
    phoneNumber: String,
) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var checkPassword by remember { mutableStateOf("") }
    var checkPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        IndiStrawHeader(marginTop = 25, backStringId = R.string.back, pressBackBtn = {
            navController.popBackStack()
        })
        HeadLineBold(
            modifier = Modifier
                .padding(start = 32.dp, top = 16.dp),
            text = stringResource(id = R.string.change_password)
        )
        IndiStrawTextField(
            modifier = Modifier.padding(top = 66.dp),
            hint = stringResource(id = R.string.password),
            value = password,
            onValueChange = { password = it },
            imeAction = ImeAction.Next,
            isToggleVisible = passwordVisible,
            onToggleChange = { passwordVisible = !passwordVisible }
        )
        IndiStrawTextField(
            modifier = Modifier.padding(top = 20.dp),
            hint = stringResource(id = R.string.check_password),
            value = checkPassword,
            onValueChange = { checkPassword = it },
            isToggleVisible = checkPasswordVisible,
            onToggleChange = { checkPasswordVisible = !checkPasswordVisible }
        )
        IndiStrawButton(
            modifier = Modifier.padding(top = 37.dp),
            text = stringResource(id = R.string.check)
        ) {
            navController.navigate(AppNavigationItem.Login.route) {
                popUpTo(AppNavigationItem.Intro.route)
            }
        }
    }
}