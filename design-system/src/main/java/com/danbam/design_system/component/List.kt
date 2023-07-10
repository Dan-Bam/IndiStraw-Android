package com.danbam.design_system.component

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.util.LaunchType
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import com.danbam.design_system.util.indiStrawClickable
import com.danbam.design_system.util.rememberLauncher
import com.danbam.design_system.util.typedLaunch

@Composable
fun AddImageList(
    modifier: Modifier = Modifier,
    imageList: List<String>,
    onRemove: (Int) -> Unit,
    selectGallery: (Uri?) -> Unit
) {
    val launcher = rememberLauncher(selectFile = selectGallery)
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(
                    IndiStrawTheme.colors.lightBlack,
                    IndiStrawTheme.shapes.circle
                )
                .padding(21.dp)
                .indiStrawClickable {
                    launcher.typedLaunch(launchType = LaunchType.Image)
                }
        ) {
            IndiStrawIcon(
                modifier = Modifier.align(Alignment.Center),
                icon = IndiStrawIconList.Plus
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        RemoveOverScrollLazyRow {
            itemsIndexed(imageList) { index, item ->
                Box {
                    AsyncImage(
                        modifier = Modifier
                            .padding(top = 7.dp, end = 7.dp)
                            .height(100.dp)
                            .width(160.dp)
                            .clip(IndiStrawTheme.shapes.smallRounded),
                        model = item,
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop
                    )
                    IndiStrawIcon(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .indiStrawClickable { onRemove(index) },
                        icon = IndiStrawIconList.Delete
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun AddFileList(
    fileList: List<String>,
    onDelete: (Int) -> Unit,
    selectFile: (Uri?) -> Unit
) {
    val launcher = rememberLauncher(selectFile = selectFile)
    if (fileList.isEmpty()) {
        FileItem(openFile = {
            launcher.typedLaunch(launchType = LaunchType.File)
        })
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(fileList.size) {
                FileItem(file = fileList[it], isDelete = { onDelete(it) })
                Spacer(modifier = Modifier.height(16.dp))
            }
            Column(
                modifier = Modifier.indiStrawClickable {
                    launcher.typedLaunch(launchType = LaunchType.File)
                },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 13.dp)
                        .border(
                            BorderStroke(1.dp, IndiStrawTheme.colors.white),
                            shape = IndiStrawTheme.shapes.circle
                        )
                        .padding(9.dp)
                ) {
                    IndiStrawIcon(icon = IndiStrawIconList.Plus)
                }
                TitleRegular(
                    text = stringResource(id = R.string.add_file),
                    color = IndiStrawTheme.colors.main,
                    fontSize = 14
                )
            }
        }
    }
}

@Composable
fun AddPeopleList(
    modifier: Modifier = Modifier,
    onAddPeople: () -> Unit,
    peopleList: List<String>,
    onRemove: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 15.dp)
                .background(
                    IndiStrawTheme.colors.lightBlack,
                    IndiStrawTheme.shapes.bigRounded
                )
                .width(120.dp)
                .height(140.dp)
                .indiStrawClickable(onClick = onAddPeople)
        ) {
            IndiStrawIcon(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.Center),
                icon = IndiStrawIconList.Plus
            )
        }
        Spacer(modifier = Modifier.width(9.dp))
        RemoveOverScrollLazyRow {
            itemsIndexed(peopleList) { index, item ->
                Box {
                    Column(
                        modifier = Modifier
                            .padding(top = 3.dp, end = 3.dp)
                            .height(140.dp)
                            .width(120.dp)
                            .background(
                                IndiStrawTheme.colors.darkGray,
                                IndiStrawTheme.shapes.bigRounded
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(25.dp))
                        AsyncImage(
                            modifier = modifier
                                .size(60.dp)
                                .clip(IndiStrawTheme.shapes.circle),
                            model = item,
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        TitleSemiBold(text = "이름")
                    }
                    IndiStrawIcon(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .indiStrawClickable { onRemove(index) },
                        icon = IndiStrawIconList.Delete
                    )
                }
                Spacer(modifier = Modifier.width(9.dp))
            }
        }
    }
}