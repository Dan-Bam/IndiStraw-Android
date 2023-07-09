package com.danbam.mobile.ui.movie.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.AddPeopleList
import com.danbam.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.R
import com.danbam.design_system.component.IndiStrawButton

sealed class AddPeopleType {
    object Director : AddPeopleType()
    object Actor : AddPeopleType()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddActorScreen(
    navController: NavController
) {
    var addPeopleType: AddPeopleType by remember { mutableStateOf(AddPeopleType.Director) }
    IndiStrawBottomSheetLayout(sheetContent = {
        Divider(
            modifier = Modifier
                .padding(top = 53.dp)
                .height(1.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray2)
        )
        TitleRegular(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 20.dp),
            text = stringResource(id = if (addPeopleType == AddPeopleType.Director) R.string.add_search_director else R.string.add_search_actor)
        )
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray2)
        )
        TitleRegular(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 20.dp),
            text = stringResource(id = if (addPeopleType == AddPeopleType.Director) R.string.add_new_director else R.string.add_new_actor)
        )
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.gray2)
        )
        Spacer(modifier = Modifier.height(140.dp))
    }) { _, openBottomSheet ->
        IndiStrawColumnBackground {
            IndiStrawHeader(
                pressBackBtn = { navController.popBackStack() }
            )
            TitleRegular(
                modifier = Modifier.padding(start = 15.dp, top = 50.dp, bottom = 16.dp),
                text = stringResource(id = R.string.add_director)
            )
            AddPeopleList(
                onAddPeople = {
                    addPeopleType = AddPeopleType.Director
                    openBottomSheet()
                },
                peopleList = listOf(
                    "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670"
                ),
                onRemove = {})
            TitleRegular(
                modifier = Modifier.padding(start = 15.dp, top = 50.dp, bottom = 16.dp),
                text = stringResource(id = R.string.add_actor)
            )
            AddPeopleList(
                onAddPeople = {
                    addPeopleType = AddPeopleType.Actor
                    openBottomSheet()
                },
                peopleList = listOf(
                    "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670"
                ),
                onRemove = {})
            Spacer(modifier = Modifier.weight(1F))
            IndiStrawButton(text = stringResource(id = R.string.check)) {

            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}