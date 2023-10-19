package com.danbam.indistraw.feature.mobile.profile.find_address

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.design_system.component.IndiStrawSearchTextField
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.DialogMedium
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.util.androidx.RemoveOverScrollLazyColumn
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.util.androidx.popBackStack
import com.danbam.indistraw.feature.mobile.navigation.profile.ProfileDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.profile.ProfileNavigationItem

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
            backIconSize = 22,
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
                            .padding(start = 15.dp, end = 15.dp, top = 36.dp)
                    ) {
                        items(it.itemCount) { index ->
                            it[index]?.let {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .indiStrawClickable {
                                            focusManager.clearFocus()
                                            keyboardController?.hide()
                                            navController.navigate(ProfileNavigationItem.DetailAddress.route + ProfileDeepLinkKey.ADDRESS + "${it.streetAddress} ${it.buildName}" + ProfileDeepLinkKey.ZIP_CODE + it.zipcode)
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    IndiStrawIcon(icon = IndiStrawIconList.Search)
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Column {
                                        DialogMedium(text = it.streetAddress)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        if (it.buildName.isNotEmpty()) {
                                            TitleRegular(
                                                text = it.buildName,
                                                fontSize = 14,
                                                color = IndiStrawTheme.colors.gray
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.weight(1F))
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