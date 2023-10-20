package com.danbam.indistraw.core.ui.exoplayer

import android.view.KeyEvent
import androidx.media3.common.C
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.Player.STATE_IDLE
import androidx.media3.exoplayer.ExoPlayer

fun ExoPlayer.detectKeyEvent(
    event: KeyEvent,
    onShowController: () -> Unit,
    onFinish: (Long) -> Unit
): Boolean {
    val keyCode: Int = event.keyCode
    if (event.action == KeyEvent.ACTION_DOWN) {
        if (keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD) {
            if (playbackState != STATE_ENDED) {
                seekForward()
            }
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_REWIND) {
            seekBack()
        } else if (event.repeatCount == 0) {
            if (keyCode != KeyEvent.KEYCODE_BACK) {
                onShowController()
            }
            when (keyCode) {
                KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, KeyEvent.KEYCODE_HEADSETHOOK -> dispatchPlayPause()
                KeyEvent.KEYCODE_MEDIA_PLAY -> dispatchPlay()
                KeyEvent.KEYCODE_MEDIA_PAUSE -> pause()
                KeyEvent.KEYCODE_MEDIA_NEXT -> seekToNext()
                KeyEvent.KEYCODE_MEDIA_PREVIOUS -> seekToPrevious()
                KeyEvent.KEYCODE_BACK -> onFinish(currentPosition)
                else -> {}
            }
        }
    }
    return false
}

private fun ExoPlayer.dispatchPlayPause() {
    val state = playbackState
    if (state == STATE_IDLE || state == STATE_ENDED || !playWhenReady) {
        dispatchPlay()
    } else {
        pause()
    }
}

private fun ExoPlayer.dispatchPlay() {
    val state = playbackState
    if (state == STATE_IDLE) {
        prepare()
    } else if (state == STATE_ENDED) {
        seekTo(currentMediaItemIndex, C.TIME_UNSET)
    }
    play()
}