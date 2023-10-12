package com.danbam.indistraw.app.mobile.ui.movie.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.AddImageList
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.component.IndiStrawTextField
import com.danbam.indistraw.core.design_system.component.IndiStrawToggle
import com.danbam.indistraw.core.design_system.component.SelectImageButton
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.util.android.toFile
import com.danbam.indistraw.core.design_system.util.androidx.LaunchType
import com.danbam.indistraw.core.design_system.util.androidx.getActivity
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.util.androidx.rememberLauncher
import com.danbam.indistraw.core.design_system.util.androidx.typedLaunch
import com.danbam.indistraw.app.mobile.ui.movie.navigation.MovieNavigationItem
import com.danbam.indistraw.core.design_system.util.androidx.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun WriteIntroduceScreen(
    navController: NavController,
    makeMovieViewModel: MakeMovieViewModel = hiltViewModel(getActivity())
) {
    val container = makeMovieViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val context = LocalContext.current
    var title by remember { mutableStateOf(state.title) }
    var description by remember { mutableStateOf(state.description) }
    var thumbnailUrl: String? by remember { mutableStateOf(state.thumbnailUrl) }
    var movieUrl: String? by remember { mutableStateOf(state.movieUrl) }
    var isCrowdFunding by remember { mutableStateOf(state.isFunding) }
    val imageList = remember { mutableStateListOf(*state.imageList.toTypedArray()) }
    val launcher = rememberLauncher(selectFile = {
        it?.let {
            makeMovieViewModel.uploadFile(it.toFile(context)) {
                movieUrl = it.split("/").last()
            }
        }
    })

    sideEffect.observeWithLifecycle {
        if (it is MakeMovieSideEffect.Next) {
            navController.navigate(MovieNavigationItem.AddActor.route)
        }
    }

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
                    makeMovieViewModel.uploadFile(it.toFile(context)) {
                        thumbnailUrl = it
                    }
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
                text = movieUrl ?: stringResource(id = R.string.require_movie),
                color = IndiStrawTheme.colors.gray,
                maxLines = 1
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
            imageList = imageList,
            onRemove = { imageList.removeAt(it) }) {
            it?.let {
                makeMovieViewModel.uploadFile(it.toFile(context)) {
                    imageList.add(it)
                }
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        IndiStrawButton(text = stringResource(id = R.string.next)) {
            makeMovieViewModel.saveIntroduce(
                thumbnailUrl = thumbnailUrl,
                movieUrl = movieUrl,
                title = title,
                description = description,
                isFunding = isCrowdFunding,
                imageList = imageList
            )
        }
        Spacer(modifier = Modifier.height(79.dp))
    }
}