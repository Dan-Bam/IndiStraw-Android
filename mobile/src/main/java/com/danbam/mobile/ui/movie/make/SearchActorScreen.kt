package com.danbam.mobile.ui.movie.make

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.DialogMedium
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawSearchTextField
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.mobile.util.view.popBackStack

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchActorScreen(
    navController: NavController
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var search by remember { mutableStateOf("") }

    IndiStrawColumnBackground {
        IndiStrawHeader(
            backIconSize = 22,
            isBackString = false,
            pressBackBtn = { navController.popBackStack(keyboardController = keyboardController) }
        ) {
            IndiStrawSearchTextField(
                hint = stringResource(id = R.string.looking_for),
                value = search,
                onValueChange = {
                    search = it
                }
            ) {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        RemoveOverScrollLazyColumn {
            items(30) {
                Row(
                    modifier = Modifier.padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(IndiStrawTheme.shapes.circle),
                        model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                        contentDescription = "profileUrl",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    DialogMedium(text = "백승민")
                    Spacer(modifier = Modifier.weight(1F))
                    IndiStrawIcon(icon = IndiStrawIconList.FastSearch)
                }
            }
        }
    }
}