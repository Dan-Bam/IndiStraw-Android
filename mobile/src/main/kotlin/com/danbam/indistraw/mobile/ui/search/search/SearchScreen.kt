package com.danbam.indistraw.mobile.ui.search.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danbam.indistraw.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.design_system.component.IndiStrawHeader
import com.danbam.indistraw.design_system.component.IndiStrawSearchTextField
import com.danbam.indistraw.design_system.R
import com.danbam.indistraw.mobile.ui.search.result_search.ResultSearchScreen
import com.danbam.indistraw.mobile.ui.search.searching.SearchingScreen
import com.danbam.indistraw.mobile.ui.search.start_search.StartSearchScreen
import com.danbam.indistraw.mobile.util.view.popBackStack

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val container = searchViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var search by remember { mutableStateOf(state.keyword) }
    IndiStrawColumnBackground(
        onClickAction = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    ) {
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
                    searchViewModel.changeSearchType(if (search.isEmpty()) SearchType.Start else SearchType.Searching)
                }
            ) {
                focusManager.clearFocus()
                keyboardController?.hide()
                searchViewModel.changeSearchType(SearchType.Result, keyword = search)
            }
        }
        when (state.searchType) {
            is SearchType.Start -> {
                StartSearchScreen(
                    onClickAction = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ) {
                    search = it
                    searchViewModel.changeSearchType(SearchType.Result, keyword = search)
                }
            }

            is SearchType.Searching -> {
                SearchingScreen(
                    keyword = search,
                    onClickAction = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ) {
                    search = it
                    searchViewModel.changeSearchType(SearchType.Result, keyword = search)
                }
            }

            is SearchType.Result -> {
                ResultSearchScreen(
                    navController = navController,
                    keyword = search,
                    onClickAction = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                )
            }
        }
    }
}