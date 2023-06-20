package com.danbam.mobile.ui.search.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawSearchTextField
import com.danbam.design_system.R
import com.danbam.mobile.ui.search.result_search.ResultSearchScreen
import com.danbam.mobile.ui.search.searching.SearchingScreen
import com.danbam.mobile.ui.search.start_search.StartSearchScreen

sealed class SearchType {
    object Start : SearchType()
    object Searching : SearchType()
    object Result : SearchType()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var search by remember { mutableStateOf("") }
    var searchType: SearchType by remember { mutableStateOf(SearchType.Start) }
    IndiStrawColumnBackground(
        onClickAction = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    ) {
        IndiStrawHeader(
            isBackString = false,
            pressBackBtn = { navController.popBackStack() }
        ) {
            IndiStrawSearchTextField(
                hint = stringResource(id = R.string.looking_for),
                value = search,
                onValueChange = {
                    search = it
                    searchType = if (search.isEmpty()) SearchType.Start else SearchType.Searching
                }
            ) {
                focusManager.clearFocus()
                keyboardController?.hide()
                searchType = SearchType.Result
            }
        }
        when (searchType) {
            is SearchType.Start -> {
                StartSearchScreen {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    search = it
                    searchType = SearchType.Result
                }
            }

            is SearchType.Searching -> {
                SearchingScreen {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    search = it
                    searchType = SearchType.Result
                }
            }

            is SearchType.Result -> {
                ResultSearchScreen(keyword = search)
            }
        }
    }
}