package com.danbam.mobile.ui.movie.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.AddImageList
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawTextField
import com.danbam.design_system.component.IndiStrawToggle
import com.danbam.design_system.component.SelectImageButton
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.util.LaunchType
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.design_system.util.rememberLauncher
import com.danbam.design_system.util.typedLaunch
import com.danbam.mobile.ui.movie.navigation.MovieNavigationItem

@Composable
fun WriteIntroduceScreen(
    navController: NavController
) {
    val launcher = rememberLauncher(selectFile = {})
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var thumbnailUrl: String? by remember { mutableStateOf(null) }
    var isCrowdFunding by remember { mutableStateOf(false) }
    IndiStrawColumnBackground(
        scrollEnabled = true
    ) {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 17.dp, bottom = 16.dp),
            text = stringResource(id = R.string.thumbnail)
        )
        SelectImageButton(
            imageUrl = thumbnailUrl,
            selectGallery = {
                it?.let {

                }
            })
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.movie)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.darkGray, IndiStrawTheme.shapes.defaultRounded)
                .padding(horizontal = 13.dp, vertical = 18.dp)
                .indiStrawClickable { launcher.typedLaunch(launchType = LaunchType.Video) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IndiStrawIcon(icon = IndiStrawIconList.Movie)
            Spacer(modifier = Modifier.width(8.dp))
            ExampleTextMedium(
                text = stringResource(id = R.string.require_movie),
                color = IndiStrawTheme.colors.gray
            )
        }
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.title)
        )
        IndiStrawTextField(
            hint = stringResource(id = R.string.require_title),
            value = title,
            onValueChange = { title = it })
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.introduce)
        )
        IndiStrawTextField(
            hint = stringResource(id = R.string.require_introduce),
            value = description,
            onValueChange = { description = it })
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 16.dp),
            text = stringResource(id = R.string.is_funding)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .background(IndiStrawTheme.colors.darkGray, IndiStrawTheme.shapes.defaultRounded)
                .padding(horizontal = 13.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExampleTextMedium(text = stringResource(id = R.string.is_use_crowd_funding))
            IndiStrawToggle(isChecked = isCrowdFunding, onChecked = { isCrowdFunding = it })
        }
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp),
            text = stringResource(id = R.string.add_image)
        )
        Spacer(modifier = Modifier.height(12.dp))
        AddImageList(
            modifier = Modifier.padding(start = 15.dp),
            imageList = listOf(),
            onRemove = { }) {
            it?.let {

            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        IndiStrawButton(text = stringResource(id = R.string.next)) {
            navController.navigate(MovieNavigationItem.AddActor.route)
        }
        Spacer(modifier = Modifier.height(79.dp))
    }
}