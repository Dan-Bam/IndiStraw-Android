package com.danbam.indistraw.core.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.util.indiStrawClickable

@Composable
fun IndiStrawDialog(
    visible: Boolean,
    title: String,
    content: String,
    onDismissRequest: () -> Unit,
    onApprove: () -> Unit,
) {
    if (visible) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = onDismissRequest
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .fillMaxWidth()
                    .background(
                        color = IndiStrawTheme.colors.darkGray2,
                        shape = IndiStrawTheme.shapes.bigRounded
                    )
                    .padding(start = 25.dp, end = 25.dp, top = 25.dp, bottom = 16.dp)
            ) {
                HeadLineBold(text = title, fontSize = 18)
                Spacer(modifier = Modifier.height(12.dp))
                DialogMedium(text = content, color = IndiStrawTheme.colors.gray)
                Spacer(modifier = Modifier.height(35.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TitleSemiBold(
                        modifier = Modifier.indiStrawClickable(onClick = onDismissRequest),
                        text = stringResource(id = R.string.cancel),
                        color = IndiStrawTheme.colors.main,
                        fontSize = 16
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    TitleSemiBold(
                        modifier = Modifier.indiStrawClickable(onClick = onApprove),
                        text = stringResource(id = R.string.approve),
                        color = IndiStrawTheme.colors.main,
                        fontSize = 16
                    )
                }
            }
        }
    }
}

@Composable
fun IndiStrawTvDialog(
    visible: Boolean,
    content: String,
    onDismissRequest: () -> Unit,
    onOkay: () -> Unit = onDismissRequest,
) {
    if (visible) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = onDismissRequest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.6F)
                    .fillMaxHeight(0.5F)
                    .background(
                        color = IndiStrawTheme.colors.lightBlack.copy(alpha = 0.9F),
                        shape = IndiStrawTheme.shapes.defaultRounded
                    )
                    .padding(vertical = 60.dp, horizontal = 60.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ExampleTextRegular(text = content, fontSize = 35)
                ExampleTextRegular(
                    modifier = Modifier
                        .align(End)
                        .background(
                            color = IndiStrawTheme.colors.main,
                            shape = IndiStrawTheme.shapes.defaultRounded
                        )
                        .padding(horizontal = 30.dp, vertical = 15.dp)
                        .indiStrawClickable(onClick = onOkay),
                    text = stringResource(id = R.string.approve),
                    fontSize = 35
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun IndiStrawTvTitleDialog(
    visible: Boolean,
    title: String,
    content: String,
    onDismissRequest: () -> Unit,
    onOkay: () -> Unit,
) {
    if (visible) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = onDismissRequest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.6F)
                    .fillMaxHeight(0.5F)
                    .background(
                        color = IndiStrawTheme.colors.lightBlack.copy(alpha = 0.9F),
                        shape = IndiStrawTheme.shapes.defaultRounded
                    )
                    .padding(vertical = 40.dp, horizontal = 50.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    DialogMedium(text = title, fontSize = 48)
                    Spacer(modifier = Modifier.height(10.dp))
                    ExampleTextRegular(
                        text = content,
                        fontSize = 24,
                        color = IndiStrawTheme.colors.gray
                    )
                }
                Row(
                    modifier = Modifier
                        .align(End)
                ) {
                    Surface(
                        shape = ClickableSurfaceDefaults.shape(
                            shape = IndiStrawTheme.shapes.defaultRounded
                        ),
                        scale = ClickableSurfaceDefaults.scale(
                            focusedScale = 1F
                        ),
                        color = ClickableSurfaceDefaults.color(
                            color = IndiStrawTheme.colors.darkGray,
                            focusedColor = IndiStrawTheme.colors.main,
                            pressedColor = IndiStrawTheme.colors.main,
                            disabledColor = IndiStrawTheme.colors.darkGray
                        ),
                        onClick = onDismissRequest
                    ) {
                        ExampleTextRegular(
                            modifier = Modifier
                                .padding(horizontal = 25.dp, vertical = 7.dp),
                            text = stringResource(id = R.string.cancel),
                            fontSize = 35
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    Surface(
                        shape = ClickableSurfaceDefaults.shape(
                            shape = IndiStrawTheme.shapes.defaultRounded
                        ),
                        scale = ClickableSurfaceDefaults.scale(
                            focusedScale = 1F
                        ),
                        color = ClickableSurfaceDefaults.color(
                            color = IndiStrawTheme.colors.darkGray,
                            focusedColor = IndiStrawTheme.colors.main,
                            pressedColor = IndiStrawTheme.colors.main,
                            disabledColor = IndiStrawTheme.colors.darkGray
                        ),
                        onClick = onOkay
                    ) {
                        ExampleTextRegular(
                            modifier = Modifier.padding(horizontal = 25.dp, vertical = 7.dp),
                            text = stringResource(id = R.string.approve),
                            fontSize = 35
                        )
                    }
                }
            }
        }
    }
}