package com.danbam.mobile.ui.movie.make

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.SelectProfileButton
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.R
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawTextField

@Composable
fun WriteActorScreen(
    navController: NavController
) {
    var name: String by remember { mutableStateOf("") }
    IndiStrawColumnBackground {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
        SelectProfileButton(
            modifier = Modifier
                .padding(top = 22.dp)
                .align(Alignment.CenterHorizontally),
            paddingValues = PaddingValues(22.dp),
            imageUrl = null,
            isSignUp = true,
            selectGallery = {
                it?.let { }
            },
            selectCamera = {
                it?.let { }
            }) {
            Spacer(modifier = Modifier.height(43.dp))
            TitleRegular(Modifier.padding(start = 15.dp), text = stringResource(id = R.string.name))
            Spacer(modifier = Modifier.height(16.dp))
            IndiStrawTextField(
                hint = stringResource(id = R.string.require_name),
                value = name,
                onValueChange = { name = it })
            Spacer(modifier = Modifier.height(36.dp))
            IndiStrawButton(text = stringResource(id = R.string.check)) {
                navController.popBackStack()
            }
        }
    }
}