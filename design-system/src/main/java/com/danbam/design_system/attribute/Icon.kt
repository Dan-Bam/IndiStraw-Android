package com.danbam.design_system.attribute

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.danbam.design_system.R
import javax.annotation.concurrent.Immutable

@Immutable
class Icon private constructor(
    @DrawableRes val drawableId: Int,
    val contentDescription: String? = null,
) {
    companion object {
        @Stable
        val OpenEyes = Icon(
            drawableId = R.drawable.ic_open_eyes,
            contentDescription = "open eyes"
        )

        @Stable
        val CloseEyes = Icon(
            drawableId = R.drawable.ic_close_eyes,
            contentDescription = "close eyes"
        )

        @Stable
        val Back = Icon(
            drawableId = R.drawable.ic_back,
            contentDescription = "back"
        )

        @Stable
        val down = Icon(
            drawableId = R.drawable.ic_down,
            contentDescription = "down"
        )

        @Stable
        val dateUp = Icon(
            drawableId = R.drawable.ic_date_up,
            contentDescription = "date up"
        )

        @Stable
        val dateDown = Icon(
            drawableId = R.drawable.ic_date_down,
            contentDescription = "date down"
        )

        @Stable
        val fastSearch = Icon(
            drawableId = R.drawable.ic_fast_search,
            contentDescription = "fast search"
        )

        @Stable
        val Gallery = Icon(
            drawableId = R.drawable.ic_gallery,
            contentDescription = "gallery"
        )

        @Stable
        val Camera = Icon(
            drawableId = R.drawable.ic_camera,
            contentDescription = "camera"
        )

        @Stable
        val search = Icon(
            drawableId = R.drawable.ic_search,
            contentDescription = "search"
        )

        @Stable
        val Profile = Icon(
            drawableId = R.drawable.ic_profile,
            contentDescription = "profile"
        )

        @Stable
        val noImage = Icon(
            drawableId = R.drawable.ic_no_img,
            contentDescription = "no image"
        )
    }
}