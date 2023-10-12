package com.danbam.indistraw.design_system.component

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.danbam.indistraw.design_system.IndiStrawTheme
import com.danbam.indistraw.design_system.R
import com.danbam.indistraw.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.design_system.util.LaunchType
import com.danbam.indistraw.design_system.util.checkAndRequestPermissions
import com.danbam.indistraw.design_system.util.indiStrawClickable
import com.danbam.indistraw.design_system.util.rememberLauncher
import com.danbam.indistraw.design_system.util.typedLaunch

sealed class Shape {
    object None : Shape()
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
            .padding(horizontal = 15.dp)
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
fun IndiStrawTvButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    JoinBold(
        modifier = modifier
            .fillMaxWidth(0.28F)
            .onFocusChanged {
                focused = it.hasFocus || it.isFocused
            }
            .background(
                color = if (focused) IndiStrawTheme.colors.main else Color.Transparent,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .border(
                width = 3.dp,
                color = if (focused) Color.Transparent else IndiStrawTheme.colors.white,
                shape = IndiStrawTheme.shapes.defaultRounded
            )
            .indiStrawClickable(rippleColor = IndiStrawTheme.colors.black, onClick = onClick)
            .padding(vertical = 10.dp),
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 24
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
        Shape.None -> RoundedCornerShape(ZeroCornerSize)
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
fun SelectProfileButton(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    isSignUp: Boolean = false,
    imageUrl: String?,
    selectCamera: (Bitmap?) -> Unit,
    selectGallery: (Uri?) -> Unit,
    bottomContent: @Composable () -> Unit = {},
) {
    val context = LocalContext.current
    val launcher = rememberLauncher(selectFile = selectGallery)
    val takePhotoFromCameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { takenPhoto ->
            selectCamera(takenPhoto)
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
            ChooseImageItem(
                icon = IndiStrawIconList.Gallery,
                text = stringResource(id = R.string.choose_gallery)
            ) {
                launcher.typedLaunch(launchType = LaunchType.Image)
            }
            Spacer(modifier = Modifier.height(40.dp))
            ChooseImageItem(
                icon = IndiStrawIconList.Camera,
                text = stringResource(id = R.string.choose_camera)
            ) {
                if (checkAndRequestPermissions(
                        context = context,
                        permissions = arrayOf(Manifest.permission.CAMERA),
                    )
                ) {
                    takePhotoFromCameraLauncher.launch()
                } else {
                    launcherMultiplePermissions.launch(arrayOf(Manifest.permission.CAMERA))
                }
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
                                    .size(if (isSignUp) 125.dp else 80.dp)
                                    .clip(shape = IndiStrawTheme.shapes.circle),
                                model = imageUrl, contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                        }
                    } else {
                        Box(
                            modifier = modifier
                                .background(
                                    color = IndiStrawTheme.colors.gray,
                                    shape = IndiStrawTheme.shapes.circle
                                )
                                .padding(paddingValues = paddingValues),
                        ) {
                            IndiStrawIcon(
                                modifier = Modifier
                                    .align(Center)
                                    .size(if (isSignUp) 56.dp else 35.dp),
                                icon = IndiStrawIconList.NoImage
                            )
                        }
                        IndiStrawIcon(
                            modifier = Modifier
                                .align(BottomEnd)
                                .size(if (isSignUp) 40.dp else 25.dp),
                            icon = IndiStrawIconList.PlusCircle
                        )
                    }
                }
            }
            bottomContent()
        }
    }
}

@Composable
fun SelectImageButton(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    selectGallery: (Uri?) -> Unit,
) {
    val launcher = rememberLauncher(selectFile = selectGallery)
    val thumbnailHeight = LocalConfiguration.current.screenHeightDp * 0.17

    if (imageUrl != null) {
        ImageButton(
            modifier = Modifier
                .height(thumbnailHeight.dp)
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            imgSrc = imageUrl,
            shape = Shape.Rectangle
        ) {
            launcher.typedLaunch(launchType = LaunchType.Image)
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(thumbnailHeight.dp)
                .padding(horizontal = 15.dp)
                .background(
                    color = IndiStrawTheme.colors.darkGray3,
                    shape = IndiStrawTheme.shapes.bigRounded
                )
                .indiStrawClickable {
                    launcher.typedLaunch(launchType = LaunchType.Image)
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TitleSemiBold(
                text = stringResource(id = R.string.require_thumbnail),
                color = IndiStrawTheme.colors.white,
                fontSize = 16
            )
            Spacer(modifier = Modifier.height(29.dp))
            Row(
                modifier = Modifier
                    .background(
                        color = IndiStrawTheme.colors.main,
                        shape = IndiStrawTheme.shapes.bigRounded
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = CenterVertically
            ) {
                IndiStrawIcon(icon = IndiStrawIconList.Plus)
                Spacer(modifier = Modifier.width(6.dp))
                TitleRegular(text = stringResource(id = R.string.upload_image), fontSize = 14)
            }
        }
    }
}

@Composable
private fun ChooseImageItem(
    modifier: Modifier = Modifier,
    icon: IndiStrawIconList,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .indiStrawClickable(onClick = onClick)
            .padding(start = 32.dp)
    ) {
        IndiStrawIcon(icon = icon)
        Spacer(modifier = Modifier.width(16.dp))
        TitleRegular(
            modifier = Modifier.align(CenterVertically),
            text = text
        )
    }
}