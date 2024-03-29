package com.danbam.indistraw.feature.mobile.funding.make

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danbam.indistraw.core.design_system.component.IndiStrawButton
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.ui.component.AddImageList
import com.danbam.indistraw.core.design_system.component.IndiStrawTextField
import com.danbam.indistraw.core.design_system.component.SelectImageButton
import com.danbam.indistraw.core.design_system.util.android.toFile

@Composable
fun WriteIntroduceScreen(
    makeFundingViewModel: MakeFundingViewModel,
    onNext: () -> Unit
) {
    val container = makeFundingViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val context = LocalContext.current
    var title by remember { mutableStateOf(state.title) }
    var description by remember { mutableStateOf(state.description) }
    var thumbnailUrl: String? by remember { mutableStateOf(state.thumbnailUrl) }
    var isLoading by remember { mutableStateOf(false) }
    val imageList = remember { mutableStateListOf(*state.imageList.toTypedArray()) }
    Spacer(modifier = Modifier.height(36.dp))
    IndiStrawColumnBackground(
        isLoading = isLoading,
        scrollEnabled = true
    ) {
        TitleRegular(
            modifier = Modifier.padding(start = 15.dp, bottom = 16.dp),
            text = stringResource(id = R.string.thumbnail)
        )
        SelectImageButton(
            imageUrl = thumbnailUrl,
            selectGallery = {
                it?.let {
                    isLoading = true
                    makeFundingViewModel.uploadImage(it.toFile(context)) { thumbnail ->
                        isLoading = false
                        thumbnailUrl = thumbnail
                    }
                }
            })
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
            modifier = Modifier.padding(start = 15.dp, top = 28.dp),
            text = stringResource(id = R.string.highlight)
        )
        Spacer(modifier = Modifier.height(16.dp))
        com.danbam.indistraw.core.ui.component.AddImageList(
            modifier = Modifier.padding(start = 15.dp),
            imageList = imageList,
            onRemove = { imageList.removeAt(it) }) {
            it?.let {
                isLoading = true
                makeFundingViewModel.uploadImage(it.toFile(context)) {
                    isLoading = false
                    imageList.add(it)
                }
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        IndiStrawButton(text = stringResource(id = R.string.next)) {
            makeFundingViewModel.saveIntroduce(
                thumbnailUrl = thumbnailUrl,
                title = title,
                description = description,
                imageList = imageList,
                onNext = onNext
            )
        }
    }
}