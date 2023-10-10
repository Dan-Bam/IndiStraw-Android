package com.danbam.design_system.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.indiStrawClickable(
    runIf: Boolean = true,
    rippleColor: Color? = null,
    onLongClick: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
): Modifier = runIf(runIf) {
    composed {
        val multipleEventsCutter = remember { MultipleEventsCutter.get() }

        combinedClickable(
            onClick = {
                onClick?.let {
                    multipleEventsCutter.processEvent {
                        it()
                    }
                }
            },
            onLongClick = onLongClick,
            indication = rippleColor?.let {
                rememberRipple(
                    color = it
                )
            },
            interactionSource = remember { MutableInteractionSource() },
        )
    }
}


private const val ClickEventDelayTime: Long = 300L

internal interface MultipleEventsCutter {
    fun processEvent(event: () -> Unit)

    companion object
}

internal fun MultipleEventsCutter.Companion.get(): MultipleEventsCutter =
    MultipleEventsCutterImpl()

private class MultipleEventsCutterImpl : MultipleEventsCutter {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= ClickEventDelayTime) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}

internal inline fun <T> T.runIf(condition: Boolean, run: T.() -> T) = if (condition) {
    run()
} else this