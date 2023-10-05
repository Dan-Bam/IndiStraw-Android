package com.danbam.design_system.component

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.danbam.design_system.BuildConfig
import com.danbam.design_system.databinding.IndistrawPlayerBinding
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout

@Composable
fun IndiStrawPlayer(
    modifier: Modifier = Modifier,
    videoUrl: String,
    position: Float,
    onDispose: (Long) -> Unit
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                val mediaItem = MediaItem.Builder()
                    .setUri("${BuildConfig.VIDEO_PRE_PATH}$videoUrl")
                    .build()
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
                seekTo((position * 1000).toLong())
            }
    }

    BackHandler {
        onDispose(exoPlayer.currentPosition)
    }

    DisposableEffect(
        AndroidViewBinding(
            modifier = modifier,
            factory = IndistrawPlayerBinding::inflate
        ) {
            this.exoPlayer.apply {
                hideController()
                useController = true
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                player = exoPlayer
            }
        }) {
        onDispose {
            exoPlayer.release()
        }
    }
}