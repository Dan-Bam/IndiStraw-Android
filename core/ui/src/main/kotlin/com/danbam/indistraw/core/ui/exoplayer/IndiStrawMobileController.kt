package com.danbam.indistraw.core.ui.exoplayer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.DialogMedium
import com.danbam.indistraw.core.design_system.component.ExampleTextRegular
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.util.internal.formatMinSec
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IndiStrawMobileController(
    exoPlayer: ExoPlayer,
    movieName: String,
    isVisible: Boolean,
    isLock: Boolean,
    isPlaying: Boolean,
    onBack: () -> Unit,
    onForward: () -> Unit,
    onPause: () -> Unit,
    onFinish: () -> Unit,
    onLock: () -> Unit,
    onPIP: () -> Unit,
    onTouchPlayer: () -> Unit,
    onSeekChanged: (Float) -> Unit,
) {
    val backInteractionSource = remember { MutableInteractionSource() }
    val forwardInteractionSource = remember { MutableInteractionSource() }
    AnimatedVisibility(visible = isVisible, enter = fadeIn(), exit = fadeOut()) {
        if (isLock) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 30.dp)
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight: Int -> -fullHeight }
                            ),
                            exit = shrinkVertically()
                        )
                ) {
                    IndiStrawIcon(
                        modifier = Modifier.indiStrawClickable { onLock() },
                        icon = IndiStrawIconList.PlayerLockClose
                    )
                }
            }
        } else {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1F)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { onTouchPlayer() },
                                onDoubleTap = {
                                    val press = PressInteraction.Press(it)
                                    MainScope().launch {
                                        backInteractionSource.emit(press)
                                        delay(5000)
                                        backInteractionSource.emit(PressInteraction.Release(press))
                                    }
                                    onBack()
                                }
                            )
                        }
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(300.dp))
                            .indication(backInteractionSource, LocalIndication.current)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1F)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { onTouchPlayer() },
                                onDoubleTap = {
                                    val press = PressInteraction.Press(it)
                                    MainScope().launch {
                                        forwardInteractionSource.emit(press)
                                        delay(500)
                                        forwardInteractionSource.emit(PressInteraction.Release(press))
                                    }
                                    onForward()
                                }
                            )
                        }
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(300.dp))
                            .indication(forwardInteractionSource, LocalIndication.current)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 30.dp)
            ) {
                ControllerTop(modifier = Modifier
                    .align(Alignment.TopCenter)
                    .animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight: Int -> -fullHeight }
                        ),
                        exit = shrinkVertically()
                    ),
                    movieName = movieName,
                    onFinish = onFinish,
                    onLock = onLock,
                    onPIP = onPIP)
                ControllerMiddle(
                    modifier = Modifier.align(Alignment.Center),
                    isPlaying = isPlaying,
                    onPause = onPause,
                    onBack = onBack,
                    onForward = onForward
                )
                ControllerBottom(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight: Int -> fullHeight }
                        ),
                        exit = slideOutVertically(
                            targetOffsetY = { fullHeight: Int -> fullHeight }
                        )
                    ), exoPlayer = exoPlayer, onSeekChanged = onSeekChanged)
            }
        }
    }
}

@Composable
private fun ControllerTop(
    modifier: Modifier,
    movieName: String,
    onFinish: () -> Unit,
    onLock: () -> Unit,
    onPIP: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        DialogMedium(
            modifier = Modifier.align(Alignment.Center),
            text = movieName
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IndiStrawIcon(
                modifier = Modifier.indiStrawClickable { onFinish() },
                icon = IndiStrawIconList.PlayerFinish
            )
            Spacer(modifier = Modifier.weight(1F))
            IndiStrawIcon(
                modifier = Modifier.indiStrawClickable { onPIP() },
                icon = IndiStrawIconList.PlayerPIP
            )
            Spacer(modifier = Modifier.width(30.dp))
            IndiStrawIcon(
                modifier = Modifier.indiStrawClickable { onLock() },
                icon = IndiStrawIconList.PlayerLockOpen
            )
        }
    }
}

@Composable
private fun ControllerMiddle(
    modifier: Modifier,
    isPlaying: Boolean,
    onPause: () -> Unit,
    onBack: () -> Unit,
    onForward: () -> Unit,
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(2F))
        IndiStrawIcon(
            modifier = Modifier.indiStrawClickable { onBack() },
            icon = IndiStrawIconList.PlayerBack
        )
        Spacer(modifier = Modifier.weight(1F))
        IndiStrawIcon(
            modifier = Modifier.indiStrawClickable { onPause() },
            icon = if (isPlaying) IndiStrawIconList.PlayerPlay else IndiStrawIconList.PlayerStop
        )
        Spacer(modifier = Modifier.weight(1F))
        IndiStrawIcon(
            modifier = Modifier.indiStrawClickable { onForward() },
            icon = IndiStrawIconList.PlayerForward
        )
        Spacer(modifier = Modifier.weight(2F))
    }
}

@Composable
private fun ControllerBottom(
    modifier: Modifier,
    exoPlayer: ExoPlayer,
    onSeekChanged: (Float) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1F)
            ) {
                Slider(
                    value = exoPlayer.bufferedPercentage.toFloat(),
                    enabled = false,
                    onValueChange = { },
                    valueRange = 0f..100f,
                    colors =
                    SliderDefaults.colors(
                        disabledThumbColor = Color.Transparent,
                        disabledActiveTrackColor = Color.Gray
                    )
                )
                Slider(
                    value = exoPlayer.currentPosition.toFloat(),
                    onValueChange = onSeekChanged,
                    valueRange = 0f..exoPlayer.duration.toFloat(),
                    colors = SliderDefaults.colors(
                        thumbColor = IndiStrawTheme.colors.main,
                        activeTrackColor = IndiStrawTheme.colors.main
                    )
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            ExampleTextRegular(
                text = (exoPlayer.duration - exoPlayer.currentPosition).formatMinSec(),
                fontSize = 14
            )
        }
    }
}