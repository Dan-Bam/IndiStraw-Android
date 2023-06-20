package com.danbam.mobile.ui.profile.find_address

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawSearchTextField
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.DialogMedium
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.util.RemoveOverScrollLazyColumn
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.mobile.ui.profile.navigation.ProfileDeepLinkKey
import com.danbam.mobile.ui.profile.navigation.ProfileNavigationItem
import com.danbam.mobile.util.view.popBackStack

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FindAddressScreen(
    navController: NavController,
    findAddressViewModel: FindAddressViewModel = hiltViewModel(),
) {
    val container = findAddressViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var address by remember { mutableStateOf("") }

    val findAddressPager = state.findAddressPager?.collectAsLazyPagingItems()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(address) {
        findAddressViewModel.getAddress(keyword = address)
    }

    IndiStrawColumnBackground(
        onClickAction = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    ) {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack(keyboardController = keyboardController) },
            isBackString = false
        ) {
            IndiStrawSearchTextField(
                hint = stringResource(id = R.string.require_address),
                value = address,
                onValueChange = { address = it }
            ) {
                focusManager.clearFocus()
                keyboardController?.hide()
                findAddressViewModel.getAddress(keyword = address)
            }
        }
        findAddressPager?.let {
            when (it.loadState.refresh) {
                is LoadState.Loading -> {
                }

                is LoadState.Error -> {
                }

                else -> {
                    RemoveOverScrollLazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp, end = 25.dp, top = 36.dp)
                    ) {
                        itemsIndexed(it) { _, item ->
                            item?.let {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .indiStrawClickable {
                                            focusManager.clearFocus()
                                            keyboardController?.hide()
                                            navController.navigate(ProfileNavigationItem.DetailAddress.route + ProfileDeepLinkKey.ADDRESS + "${item.streetAddress} ${item.buildName}" + ProfileDeepLinkKey.ZIP_CODE + item.zipcode)
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        DialogMedium(text = item.streetAddress)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        TitleRegular(
                                            text = item.buildName,
                                            fontSize = 14,
                                            color = IndiStrawTheme.colors.gray
                                        )
                                    }
                                    IndiStrawIcon(icon = IndiStrawIconList.FastSearch)
                                }
                                Spacer(modifier = Modifier.height(28.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}