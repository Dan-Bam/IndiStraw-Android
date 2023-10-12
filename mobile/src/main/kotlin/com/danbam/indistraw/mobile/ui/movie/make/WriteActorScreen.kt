package com.danbam.indistraw.mobile.ui.movie.make

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.danbam.indistraw.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.design_system.component.IndiStrawHeader
import com.danbam.indistraw.design_system.component.SelectProfileButton
import com.danbam.indistraw.design_system.component.TitleRegular
import com.danbam.indistraw.design_system.R
import com.danbam.indistraw.design_system.component.IndiStrawButton
import com.danbam.indistraw.design_system.component.IndiStrawTextField
import com.danbam.indistraw.mobile.util.android.getActivity
import com.danbam.indistraw.mobile.util.android.observeWithLifecycle
import com.danbam.indistraw.mobile.util.parser.toFile
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun WriteActorScreen(
    navController: NavController,
    addActorType: String,
    makeMovieViewModel: MakeMovieViewModel = hiltViewModel(getActivity())
) {
    val container = makeMovieViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val context = LocalContext.current
    var name: String by remember { mutableStateOf("") }
    var profileUrl: String? by remember { mutableStateOf(null) }

    sideEffect.observeWithLifecycle {
        if (it is MakeMovieSideEffect.Next) {
            navController.popBackStack()
        }
    }

    IndiStrawColumnBackground {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
        SelectProfileButton(
            modifier = Modifier
                .padding(top = 22.dp)
                .align(Alignment.CenterHorizontally),
            paddingValues = PaddingValues(22.dp),
            imageUrl = profileUrl,
            isSignUp = true,
            selectGallery = {
                it?.let {
                    makeMovieViewModel.uploadFile(it.toFile(context)) {
                        profileUrl = it
                    }
                }
            },
            selectCamera = {
                it?.let {
                    makeMovieViewModel.uploadFile(it.toFile(context)) {
                        profileUrl = it
                    }
                }
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
                makeMovieViewModel.addMoviePeople(
                    actorType = addActorType,
                    name = name,
                    profileUrl = profileUrl
                )
            }
        }
    }
}