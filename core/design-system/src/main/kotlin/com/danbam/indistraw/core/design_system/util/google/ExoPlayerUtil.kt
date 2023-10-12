package com.danbam.indistraw.core.design_system.util.google

import android.view.KeyEvent
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player

fun ExoPlayer.detectKeyEvent(event: KeyEvent): Boolean {
    val keyCode: Int = event.keyCode
    if (event.action == KeyEvent.ACTION_DOWN) {
        if (keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD) {
            if (playbackState != Player.STATE_ENDED) {
                seekForward()
            }
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_REWIND) {
            seekBack()
        } else if (event.repeatCount == 0) {
            when (keyCode) {
                KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, KeyEvent.KEYCODE_HEADSETHOOK -> dispatchPlayPause()
                KeyEvent.KEYCODE_MEDIA_PLAY -> dispatchPlay()
                KeyEvent.KEYCODE_MEDIA_PAUSE -> pause()
                KeyEvent.KEYCODE_MEDIA_NEXT -> seekToNext()
                KeyEvent.KEYCODE_MEDIA_PREVIOUS -> seekToPrevious()
                else -> {}
            }
        }
    }
    return true
}

private fun ExoPlayer.dispatchPlayPause() {
    val state = playbackState
    if (state == Player.STATE_IDLE || state == Player.STATE_ENDED || !playWhenReady) {
        dispatchPlay()
    } else {
        pause()
    }
}

private fun ExoPlayer.dispatchPlay() {
    val state = playbackState
    if (state == Player.STATE_IDLE) {
        prepare()
    } else if (state == Player.STATE_ENDED) {
        seekTo(currentMediaItemIndex, C.TIME_UNSET)
    }
    play()
}