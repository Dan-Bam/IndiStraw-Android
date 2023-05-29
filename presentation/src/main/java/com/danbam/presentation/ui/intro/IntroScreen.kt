package com.danbam.presentation.ui.intro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.JoinBold
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.presentation.R
import com.danbam.presentation.util.AppNavigationItem
import com.danbam.presentation.util.SignUpNavigationItem

@Composable
fun IntroScreen(
    navController: NavController,
) {
    var isLogin by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLogin) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 39.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IndiStrawButton(
                    text = stringResource(id = R.string.login)
                ) {
                    navController.navigate(AppNavigationItem.Login.route)
                }
                Spacer(modifier = Modifier.height(33.dp))
                Row(
                    modifier = Modifier
                        .indiStrawClickable(onClick = {
                            navController.navigate(SignUpNavigationItem.SetName.route)
                        })
                ) {
                    TitleRegular(
                        text = stringResource(id = R.string.already_sign_up),
                        color = IndiStrawTheme.colors.exampleText,
                        fontSize = 12
                    )
                    JoinBold(
                        text = stringResource(id = R.string.sign_up),
                        modifier = Modifier.padding(horizontal = 6.dp),
                        fontSize = 12
                    )
                    TitleRegular(
                        text = stringResource(id = R.string.go),
                        color = IndiStrawTheme.colors.exampleText,
                        fontSize = 12
                    )
                }
            }
        }
    }
}