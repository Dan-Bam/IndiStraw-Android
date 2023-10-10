package com.danbam.mobile.ui.movie.make

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.mobile.util.android.getActivity
import com.danbam.mobile.util.android.observeWithLifecycle
import com.danbam.mobile.util.view.popBackStack
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalComposeUiApi::class, InternalCoroutinesApi::class)
@Composable
fun SearchActorScreen(
    navController: NavController,
    addActorType: String,
    makeMovieViewModel: MakeMovieViewModel = hiltViewModel(getActivity())
) {
    val container = makeMovieViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var search by remember { mutableStateOf("") }

    sideEffect.observeWithLifecycle {
        if (it is MakeMovieSideEffect.Next) {
            navController.popBackStack(keyboardController = keyboardController)
        }
    }

    LaunchedEffect(search) {
        makeMovieViewModel.searchMoviePeople(actorType = addActorType, name = search)
    }

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
            items(state.searchMoviePeopleList) {
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .indiStrawClickable {
                            makeMovieViewModel.selectMoviePeople(
                                addActorType,
                                it
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(IndiStrawTheme.shapes.circle),
                        model = it.profileUrl,
                        contentDescription = "profileUrl",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    DialogMedium(text = it.name)
                    Spacer(modifier = Modifier.weight(1F))
                    IndiStrawIcon(icon = IndiStrawIconList.FastSearch)
                }
            }
        }
    }
}