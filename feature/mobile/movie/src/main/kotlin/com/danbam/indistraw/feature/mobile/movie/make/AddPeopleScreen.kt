package com.danbam.indistraw.feature.mobile.movie.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.ui.component.AddPeopleList
import com.danbam.indistraw.core.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.util.androidx.getActivity
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.util.androidx.observeWithLifecycle
import com.danbam.indistraw.feature.mobile.navigation.main.MainNavigationItem
import com.danbam.indistraw.feature.mobile.navigation.movie.PeopleType
import com.danbam.indistraw.feature.mobile.navigation.movie.MovieDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.movie.MovieNavigationItem
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalMaterialApi::class, InternalCoroutinesApi::class)
@Composable
fun AddPeopleScreen(
    navController: NavController,
    makeMovieViewModel: MakeMovieViewModel = hiltViewModel(getActivity())
) {
    val container = makeMovieViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var addPeopleType: PeopleType by remember { mutableStateOf(PeopleType.DIRECTOR) }

    sideEffect.observeWithLifecycle {
        if (it is MakeMovieSideEffect.SuccessCreateMovie) {
            navController.navigate(MainNavigationItem.Main.route) {
                popUpTo(MainNavigationItem.Intro.route)
            }
        }
    }

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
                .padding(horizontal = 25.dp, vertical = 20.dp)
                .indiStrawClickable { navController.navigate(MovieNavigationItem.SearchPeople.route + MovieDeepLinkKey.PEOPLE_TYPE + addPeopleType.route + MovieDeepLinkKey.IS_ENROLL + false) },
            text = stringResource(id = if (addPeopleType == PeopleType.DIRECTOR) R.string.add_search_director else R.string.add_search_actor)
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
                .padding(horizontal = 25.dp, vertical = 20.dp)
                .indiStrawClickable { navController.navigate(MovieNavigationItem.WritePeople.route + MovieDeepLinkKey.PEOPLE_TYPE + addPeopleType.route + MovieDeepLinkKey.IS_ENROLL + false) },
            text = stringResource(id = if (addPeopleType == PeopleType.DIRECTOR) R.string.add_new_director else R.string.add_new_actor)
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
            com.danbam.indistraw.core.ui.component.AddPeopleList(
                onAddPeople = {
                    addPeopleType = PeopleType.DIRECTOR
                    openBottomSheet()
                },
                peopleList = state.directorList,
                onRemove = { makeMovieViewModel.removeMoviePeople(PeopleType.DIRECTOR, it) })
            TitleRegular(
                modifier = Modifier.padding(start = 15.dp, top = 50.dp, bottom = 16.dp),
                text = stringResource(id = R.string.add_actor)
            )
            com.danbam.indistraw.core.ui.component.AddPeopleList(
                onAddPeople = {
                    addPeopleType = PeopleType.ACTOR
                    openBottomSheet()
                },
                peopleList = state.actorList,
                onRemove = { makeMovieViewModel.removeMoviePeople(PeopleType.ACTOR, it) })
            Spacer(modifier = Modifier.weight(1F))
            IndiStrawButton(text = stringResource(id = R.string.check)) {
                makeMovieViewModel.movieCreate()
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}