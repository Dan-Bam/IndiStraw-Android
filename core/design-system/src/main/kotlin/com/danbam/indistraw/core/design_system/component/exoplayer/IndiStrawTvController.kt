package com.danbam.indistraw.core.design_system.component.exoplayer

import android.view.KeyEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.DialogMedium
import com.danbam.indistraw.core.design_system.component.ExampleTextRegular
import com.danbam.indistraw.core.design_system.util.internal.formatMinSec

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun IndiStrawTvController(
    exoPlayer: ExoPlayer,
    movieName: String,
    isVisible: Boolean,
    isPlaying: Boolean,
    onBack: () -> Unit,
    onForward: () -> Unit,
    onPause: () -> Unit,
    onFinish: () -> Unit,
    onSeekChanged: (Float) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(isVisible) {
        if (isVisible) {
            focusRequester.requestFocus()
        }
    }
    AnimatedVisibility(visible = isVisible, enter = fadeIn(), exit = fadeOut()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 35.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.TopStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PlayerIcon(onClick = onFinish, icon = IndiStrawIconList.PlayerFinish)
                Spacer(modifier = Modifier.width(10.dp))
                DialogMedium(text = movieName, fontSize = 30)
            }
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Row(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ExampleTextRegular(
                        text = exoPlayer.currentPosition.formatMinSec(),
                        fontSize = 18
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier.weight(1F)
                    ) {
                        Slider(
                            value = exoPlayer.bufferedPercentage.toFloat(),
                            enabled = false,
                            onValueChange = { },
                            valueRange = 0f..100f,
                            colors = SliderDefaults.colors(
                                disabledThumbColor = Color.Transparent,
                                disabledActiveTrackColor = Color.Gray
                            )
                        )
                        Slider(
                            modifier = Modifier.onPreviewKeyEvent {
                                it.nativeKeyEvent.let { event ->
                                    if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0) {
                                        when (event.keyCode) {
                                            KeyEvent.KEYCODE_DPAD_LEFT -> {
                                                onSeekChanged(exoPlayer.currentPosition - 1000F)
                                                return@onPreviewKeyEvent true
                                            }
                                            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                                                onSeekChanged(exoPlayer.currentPosition + 1000F)
                                                return@onPreviewKeyEvent true
                                            }
                                        }
                                    }
                                }
                                false
                            },
                            value = exoPlayer.currentPosition.toFloat(),
                            onValueChange = onSeekChanged,
                            valueRange = 0f..exoPlayer.duration.toFloat(),
                            colors = SliderDefaults.colors(
                                thumbColor = IndiStrawTheme.colors.main,
                                activeTrackColor = IndiStrawTheme.colors.main
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    ExampleTextRegular(
                        text = exoPlayer.duration.formatMinSec(),
                        fontSize = 18
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PlayerIcon(onClick = onBack, icon = IndiStrawIconList.PlayerBack)
                    Spacer(modifier = Modifier.width(10.dp))
                    PlayerIcon(
                        onClick = onPause,
                        icon = if (isPlaying) IndiStrawIconList.PlayerPlay else IndiStrawIconList.PlayerStop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    PlayerIcon(onClick = onForward, icon = IndiStrawIconList.PlayerForward)
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun PlayerIcon(
    onClick: () -> Unit,
    icon: IndiStrawIconList
) {
    Surface(
        color = ClickableSurfaceDefaults.color(
            color = Color.Transparent,
            focusedColor = Color.Transparent,
            pressedColor = Color.Transparent,
            disabledColor = Color.Transparent
        ),
        onClick = onClick
    ) {
        IndiStrawIcon(icon = icon)
    }
}