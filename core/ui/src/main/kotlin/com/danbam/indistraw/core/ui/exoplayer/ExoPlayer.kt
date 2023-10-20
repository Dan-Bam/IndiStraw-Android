package com.danbam.indistraw.core.ui.exoplayer

import android.content.pm.ActivityInfo
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.danbam.indistraw.core.ui.BuildConfig
import com.danbam.indistraw.core.design_system.util.androidx.HideSystemUI
import com.danbam.indistraw.core.design_system.util.androidx.LockScreenOrientation
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import kotlinx.coroutines.delay

@Composable
@androidx.annotation.OptIn(UnstableApi::class)
fun IndiStrawPlayer(
    modifier: Modifier = Modifier,
    movieUrl: String,
    movieName: String,
    position: Float,
    isMobile: Boolean,
    isVertical: Boolean,
    onPIP: () -> Unit,
    onDispose: (Long) -> Unit
) {
    HideSystemUI()
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setSeekBackIncrementMs(5000)
            .setSeekForwardIncrementMs(5000)
            .build()
            .apply {
                val mediaItem = MediaItem.Builder()
                    .setUri("${BuildConfig.VIDEO_PRE_PATH}$movieUrl")
                    .build()
                setMediaItem(mediaItem)
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
                prepare()
                playWhenReady = true
                seekTo((position * 1000).toLong())
            }
    }
    var isVisible by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(exoPlayer.isPlaying) }
    var isLock by remember { mutableStateOf(false) }

    if (!isVertical && isMobile) {
        LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }

    BackHandler {
        if (isVisible) {
            isVisible = false
        } else {
            onDispose(exoPlayer.currentPosition)
        }
    }

    LaunchedEffect(isVisible) {
        if (isVisible && exoPlayer.playbackState != STATE_ENDED) {
            delay(if (isMobile) 1500L else 5000L)
            isVisible = false
        }
    }

    Box(modifier = modifier
        .fillMaxSize()
        .focusable()
        .onPreviewKeyEvent {
            it.nativeKeyEvent.let {
                exoPlayer.detectKeyEvent(event = it, onShowController = {
                    isVisible = true
                }, onFinish = {
                    if (isVisible) {
                        isVisible = false
                    } else {
                        onDispose(exoPlayer.currentPosition)
                    }
                })
            }
        }
        .indiStrawClickable {
            isVisible = !isVisible
        }) {
        DisposableEffect(
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        useController = false
                        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    }
                }
            )
        ) {
            val listener = object : Player.Listener {
                override fun onEvents(player: Player, events: Player.Events) {
                    super.onEvents(player, events)
                    isPlaying = player.isPlaying
                }
            }
            exoPlayer.addListener(listener)
            onDispose {
                exoPlayer.removeListener(listener)
                exoPlayer.release()
            }
        }

        if (isMobile) {
            IndiStrawMobileController(
                exoPlayer = exoPlayer,
                movieName = movieName,
                isVisible = isVisible,
                isLock = isLock,
                isPlaying = isPlaying,
                onBack = { exoPlayer.seekBack() },
                onForward = { exoPlayer.seekForward() },
                onPause = {
                    when {
                        isPlaying -> {
                            exoPlayer.pause()
                        }

                        !isPlaying -> {
                            exoPlayer.play()
                        }
                    }
                },
                onFinish = { onDispose(exoPlayer.currentPosition) },
                onLock = { isLock = !isLock },
                onPIP = {
                    isVisible = false
                    onPIP()
                },
                onTouchPlayer = { isVisible = !isVisible },
                onSeekChanged = { exoPlayer.seekTo(it.toLong()) }
            )
        } else {
            IndiStrawTvController(
                exoPlayer = exoPlayer,
                movieName = movieName,
                isVisible = isVisible,
                isPlaying = isPlaying,
                onBack = { exoPlayer.seekBack() },
                onForward = { exoPlayer.seekForward() },
                onPause = {
                    when {
                        isPlaying -> {
                            exoPlayer.pause()
                        }

                        !isPlaying -> {
                            exoPlayer.play()
                        }
                    }
                },
                onFinish = { onDispose(exoPlayer.currentPosition) },
                onSeekChanged = { exoPlayer.seekTo(it.toLong()) }
            )
        }
    }
}