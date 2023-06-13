package com.danbam.presentation.ui.find.find_id

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.R
import com.danbam.presentation.util.view.AppNavigationItem

@Composable
fun FindIdScreen(
    navController: NavController,
    findIdViewModel: FindIdViewModel = hiltViewModel(),
    phoneNumber: String,
) {
    val container = findIdViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow


    LaunchedEffect(Unit) {
        findIdViewModel.findId(phoneNumber = phoneNumber)
    }

    IndiStrawColumnBackground {
        IndiStrawHeader(marginTop = 25, pressBackBtn = {
            navController.popBackStack()
        })
        HeadLineBold(
            modifier = Modifier
                .padding(start = 32.dp, top = 16.dp),
            text = stringResource(id = R.string.current_id)
        )
        IndiStrawTextField(
            modifier = Modifier.padding(top = 96.dp),
            hint = "",
            value = state.id,
            onValueChange = { },
            readOnly = true
        )
        IndiStrawButton(
            modifier = Modifier.padding(top = 78.dp),
            text = stringResource(id = R.string.check)
        ) {
            navController.navigate(AppNavigationItem.Login.route) {
                popUpTo(AppNavigationItem.Intro.route)
            }
        }
    }
}