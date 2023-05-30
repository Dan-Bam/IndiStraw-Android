package com.danbam.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.ExampleTextRegular
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.presentation.R
import com.danbam.presentation.util.AppNavigationItem
import com.danbam.presentation.util.CertificateType
import com.danbam.presentation.util.DeepLinkKey

@Composable
fun LoginScreen(
    navController: NavController,
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var isToggleVisible by remember { mutableStateOf(false) }

    Column {
        IndiStrawHeader(
            marginTop = 25,
            backStringId = R.string.back,
            pressBackBtn = { navController.popBackStack() })
        HeadLineBold(
            text = stringResource(id = R.string.do_login),
            modifier = Modifier.padding(start = 32.dp, top = 16.dp)
        )
        IndiStrawTextField(
            modifier = Modifier.padding(top = 97.dp),
            hint = stringResource(id = R.string.id),
            value = id,
            onValueChange = { id = it },
            imeAction = ImeAction.Next
        )
        IndiStrawTextField(
            modifier = Modifier.padding(top = 20.dp),
            hint = stringResource(id = R.string.password),
            value = password,
            onValueChange = { password = it },
            isToggleVisible = isToggleVisible,
            onToggleChange = { isToggleVisible = !isToggleVisible }
        )
        TitleRegular(
            modifier = Modifier.padding(start = 32.dp, top = 7.dp),
            text = errorText,
            color = IndiStrawTheme.colors.error,
            fontSize = 12
        )
        IndiStrawButton(
            modifier = Modifier.padding(top = 37.dp),
            text = stringResource(id = R.string.login)
        ) {
//            navController.navigate(AppNavigationItem.Main.route)
            errorText = "비밀번호는 8 ~ 20자리 사이로 입력해주세요."
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ExampleTextRegular(
                modifier = Modifier
                    .indiStrawClickable(onClick = {
                        navController.navigate(
                            route = AppNavigationItem.Certificate.route
                                    + DeepLinkKey.CERTIFICATE_TYPE + CertificateType.FIND_ID
                        )
                    }),
                text = stringResource(id = R.string.find_id),
                color = IndiStrawTheme.colors.exampleText
            )
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .height(14.dp)
                    .width(1.dp)
                    .background(IndiStrawTheme.colors.exampleText)
                    .align(CenterVertically)
            )
            ExampleTextRegular(
                modifier = Modifier
                    .indiStrawClickable(onClick = {
                        navController.navigate(
                            route = AppNavigationItem.Certificate.route
                                    + DeepLinkKey.CERTIFICATE_TYPE + CertificateType.FIND_PASSWORD
                        )
                    }),
                text = stringResource(id = R.string.find_password),
                color = IndiStrawTheme.colors.exampleText
            )
        }
    }
}