package com.danbam.design_system.component

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.checkAndRequestPermissions
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
            .indiStrawClickable(rippleColor = IndiStrawTheme.colors.black, onClick = onClick)
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
            .indiStrawClickable(onClick = onClick),
        model = imgSrc,
        contentDescription = "image",
        contentScale = ContentScale.Crop,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectImageButton(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    requireGalleryString: String,
    requireCameraString: String,
    requireString: String? = null,
    isFirst: Boolean = false,
    imageUrl: String?,
    selectCamera: (Bitmap?) -> Unit,
    selectGallery: (Uri?) -> Unit,
    bottomContent: @Composable () -> Unit = {},
) {
    val context = LocalContext.current
    val takePhotoFromCameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { takenPhoto ->
            selectCamera(takenPhoto)
        }
    val takePhotoFromAlbumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectGallery(result.data?.data)

            }
        }
    val takePhotoFromAlbumIntent =
        Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            putExtra(
                Intent.EXTRA_MIME_TYPES,
                arrayOf("image/jpeg", "image/png", "image/bmp", "image/webp")
            )
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        }
    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            takePhotoFromCameraLauncher.launch()
        } else {
        }
    }

    IndiStrawBottomSheetLayout(sheetContent = {
        Column {
            Spacer(modifier = Modifier.height(42.dp))
            Row(
                modifier = Modifier
                    .indiStrawClickable(onClick = {
                        takePhotoFromAlbumLauncher.launch(
                            takePhotoFromAlbumIntent
                        )
                    })
                    .padding(start = 32.dp)
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
                modifier = Modifier
                    .indiStrawClickable(onClick = {
                        if (checkAndRequestPermissions(
                                context = context,
                                permissions = arrayOf(Manifest.permission.CAMERA),
                            )
                        ) {
                            takePhotoFromCameraLauncher.launch()
                        } else {
                            launcherMultiplePermissions.launch(arrayOf(Manifest.permission.CAMERA))
                        }
                    })
                    .padding(start = 32.dp)
            ) {
                IndiStrawIcon(icon = IndiStrawIconList.Camera)
                Spacer(modifier = Modifier.width(16.dp))
                TitleRegular(
                    modifier = Modifier.align(CenterVertically),
                    text = requireCameraString
                )
            }
            Spacer(modifier = Modifier.height(165.dp))
        }
    }) { _, bottomSheetAction ->
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .indiStrawClickable(onClick = bottomSheetAction)
                ) {
                    if (imageUrl != null) {
                        Box(
                            modifier = modifier
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .width(if (isFirst) 125.dp else 80.dp)
                                    .height(if (isFirst) 125.dp else 80.dp)
                                    .clip(shape = if (requireString == null) IndiStrawTheme.shapes.circle else IndiStrawTheme.shapes.defaultRounded),
                                model = imageUrl, contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                        }
                    } else {
                        Box(
                            modifier = modifier
                                .background(
                                    color = IndiStrawTheme.colors.gray,
                                    shape = if (requireString == null) IndiStrawTheme.shapes.circle else IndiStrawTheme.shapes.defaultRounded
                                )
                                .padding(paddingValues = paddingValues),
                        ) {
                            IndiStrawIcon(
                                modifier = Modifier
                                    .align(Center)
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
            }
            bottomContent()
        }
    }
}