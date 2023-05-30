package com.danbam.presentation.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.presentation.R
import com.danbam.presentation.util.SignUpNavigationItem

@Composable
fun SetIdScreen(
    navController: NavController,
) {
    var id by remember { mutableStateOf("") }
    Column {
        IndiStrawHeader(marginTop = 25, backStringId = R.string.back, pressBackBtn = {
            navController.popBackStack()
        })
        HeadLineBold(
            modifier = Modifier
                .padding(start = 32.dp, top = 16.dp),
            text = stringResource(id = R.string.require_id)
        )
        IndiStrawTextField(
            modifier = Modifier.padding(top = 66.dp),
            hint = stringResource(id = R.string.id),
            value = id,
            onValueChange = { id = it })
        IndiStrawButton(
            modifier = Modifier.padding(top = 78.dp),
            text = stringResource(id = R.string.next)
        ) {
            navController.navigate(SignUpNavigationItem.SetPassword.route)
        }
    }
}