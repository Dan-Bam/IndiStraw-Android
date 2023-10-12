package com.danbam.indistraw.mobile.ui.funding.make

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
import com.danbam.indistraw.core.design_system.component.AddImageList
import com.danbam.indistraw.core.design_system.component.IndiStrawTextField
import com.danbam.indistraw.core.design_system.component.SelectImageButton
import com.danbam.indistraw.mobile.util.parser.toFile

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
    val imageList = remember { mutableStateListOf(*state.imageList.toTypedArray()) }
    Spacer(modifier = Modifier.height(36.dp))
    IndiStrawColumnBackground(
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
                    makeFundingViewModel.uploadImage(it.toFile(context)) { thumbnail ->
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
        AddImageList(
            modifier = Modifier.padding(start = 15.dp),
            imageList = imageList,
            onRemove = { imageList.removeAt(it) }) {
            it?.let {
                makeFundingViewModel.uploadImage(it.toFile(context)) {
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