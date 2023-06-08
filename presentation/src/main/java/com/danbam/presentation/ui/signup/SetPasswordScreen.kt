package com.danbam.presentation.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.FindPasswordMedium
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawCheckBox
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.presentation.R
import com.danbam.presentation.util.AppNavigationItem
import com.danbam.presentation.util.toDp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetPasswordScreen(
    navController: NavController,
) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var checkPassword by remember { mutableStateOf("") }
    var checkPasswordVisible by remember { mutableStateOf(false) }
    var isAllApprove by remember { mutableStateOf(false) }
    var sheetVisible by remember { mutableStateOf(false) }

    LaunchedEffect(sheetVisible) {
        if (!sheetVisible && isAllApprove) {
            navController.navigate(AppNavigationItem.Login.route) {
                popUpTo(AppNavigationItem.Intro.route)
            }
        }
    }

    IndiStrawBottomSheetLayout(sheetContent = {
        PersonalInformationSheet(
            isAll = isAllApprove,
            onAll = {
                isAllApprove = !isAllApprove
            },
        )
    }) { sheetState, bottomSheetAction ->
        sheetVisible = sheetState.isVisible
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            IndiStrawHeader(marginTop = 25, backStringId = R.string.back, pressBackBtn = {
                navController.popBackStack()
            })
            HeadLineBold(
                modifier = Modifier
                    .padding(start = 32.dp, top = 16.dp),
                text = stringResource(id = R.string.require_password)
            )
            IndiStrawTextField(
                modifier = Modifier.padding(top = 65.dp),
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
                bottomSheetAction()
            }
        }
    }
}

@Composable
fun PersonalInformationSheet(
    isAll: Boolean,
    onAll: () -> Unit,
) {
    var context = LocalContext.current
    var size by remember { mutableStateOf(IntSize.Zero) }
    Column {
        Row(
            modifier = Modifier
                .padding(start = 35.dp, top = 42.dp)
                .indiStrawClickable(onClick = onAll),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IndiStrawCheckBox(isCheck = isAll, isBorder = true, onClick = onAll)
            TitleSemiBold(
                modifier = Modifier.padding(start = 13.dp),
                text = stringResource(id = R.string.approve_all),
                fontSize = 18
            )
        }
        Divider(
            modifier = Modifier
                .padding(start = 35.dp, end = 35.dp, top = 17.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    color = IndiStrawTheme.colors.exampleText,
                    shape = IndiStrawTheme.shapes.smallRounded
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 35.dp, end = 35.dp, top = 23.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Row {
                IndiStrawCheckBox(isCheck = isAll, onClick = { })
                TitleRegular(
                    modifier = Modifier
                        .padding(start = 13.dp)
                        .align(CenterVertically),
                    text = stringResource(id = R.string.require_terms_of_use)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                FindPasswordMedium(
                    modifier = Modifier.onSizeChanged {
                        size = it
                    },
                    text = stringResource(id = R.string.view_detail)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Divider(
                    modifier = Modifier
                        .width(size.width.toDp(context).dp)
                        .height(1.dp)
                        .background(IndiStrawTheme.colors.exampleText)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 35.dp, end = 35.dp, top = 38.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Row {
                IndiStrawCheckBox(isCheck = isAll, onClick = { })
                TitleRegular(
                    modifier = Modifier
                        .padding(start = 13.dp)
                        .align(CenterVertically),
                    text = stringResource(id = R.string.require_personal_information)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                FindPasswordMedium(
                    modifier = Modifier.onSizeChanged {
                        size = it
                    },
                    text = stringResource(id = R.string.view_detail)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Divider(
                    modifier = Modifier
                        .width(size.width.toDp(context).dp)
                        .height(1.dp)
                        .background(IndiStrawTheme.colors.exampleText)
                )
            }
        }
        Spacer(modifier = Modifier.height(117.dp))
    }
}