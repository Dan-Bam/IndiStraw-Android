package com.danbam.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.indiStrawClickable

sealed class Shape {
    object Rectangle : Shape()
    object Circle : Shape()
}

@Composable
fun IndiStrawButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    ButtonMedium(
        modifier = modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .background(
                color = IndiStrawTheme.colors.main,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .indiStrawClickable(onClick, rippleColor = IndiStrawTheme.colors.background)
            .padding(vertical = 16.dp),
        text = text,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    imgSrc: String,
    shape: Shape,
    onClick: () -> Unit,
) {
    val clipShape = when (shape) {
        Shape.Rectangle -> IndiStrawTheme.shapes.defaultRounded
        Shape.Circle -> IndiStrawTheme.shapes.circle
    }
    AsyncImage(
        modifier = modifier
            .clip(clipShape)
            .indiStrawClickable(onClick),
        model = imgSrc,
        contentDescription = "image",
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun SelectImageButton(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    requireGalleryString: String,
    requireCameraString: String,
    requireString: String? = null,
    isFirst: Boolean = false,
    moveGallery: () -> Unit,
    moveCamera: () -> Unit,
    bottomContent: @Composable () -> Unit = {},
) {
    IndiStrawBottomSheetLayout(sheetContent = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = IndiStrawTheme.colors.bottomSheet,
                    shape = IndiStrawTheme.shapes.bottomSheet
                )
                .padding(start = 32.dp, top = 13.dp, bottom = 160.dp)
        ) {
            Divider(
                modifier = Modifier
                    .width(60.dp)
                    .height(3.dp)
                    .background(
                        color = IndiStrawTheme.colors.exampleText,
                        shape = IndiStrawTheme.shapes.smallRounded
                    )
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(42.dp))
            Row(
                modifier = Modifier.indiStrawClickable(moveGallery)
            ) {
                IndiStrawIcon(icon = IndiStrawIconList.Gallery)
                Spacer(modifier = Modifier.width(16.dp))
                TitleRegular(
                    modifier = Modifier.align(CenterVertically),
                    text = requireGalleryString
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.indiStrawClickable(moveCamera)
            ) {
                IndiStrawIcon(icon = IndiStrawIconList.Camera)
                Spacer(modifier = Modifier.width(16.dp))
                TitleRegular(
                    modifier = Modifier.align(CenterVertically),
                    text = requireCameraString
                )
            }
        }
    }) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box {
                    Column(
                        modifier = modifier
                            .background(
                                color = IndiStrawTheme.colors.exampleText,
                                shape = if (requireString == null) IndiStrawTheme.shapes.circle else IndiStrawTheme.shapes.defaultRounded
                            )
                            .padding(paddingValues = paddingValues)
                            .indiStrawClickable(it),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IndiStrawIcon(
                            modifier = Modifier
                                .width(if (isFirst) 56.dp else 35.dp)
                                .height(if (isFirst) 56.dp else 35.dp),
                            icon = IndiStrawIconList.NoImage
                        )
                        if (requireString != null) {
                            ExampleTextMedium(text = requireString)
                        }
                    }
                    IndiStrawIcon(
                        modifier = Modifier.align(BottomEnd),
                        icon = IndiStrawIconList.Plus
                    )
                }
            }
            bottomContent()
        }
    }
}