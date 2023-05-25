package com.danbam.design_system.attribute

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.danbam.design_system.R
import javax.annotation.concurrent.Immutable

@Immutable
class IndiStrawIcon private constructor(
    @DrawableRes val drawableId: Int,
    val contentDescription: String? = null,
) {
    companion object {
        @Stable
        val OpenEyes = IndiStrawIcon(
            drawableId = R.drawable.ic_open_eyes,
            contentDescription = "open eyes"
        )

        @Stable
        val CloseEyes = IndiStrawIcon(
            drawableId = R.drawable.ic_close_eyes,
            contentDescription = "close eyes"
        )

        @Stable
        val Back = IndiStrawIcon(
            drawableId = R.drawable.ic_back,
            contentDescription = "back"
        )

        @Stable
        val Down = IndiStrawIcon(
            drawableId = R.drawable.ic_down,
            contentDescription = "down"
        )

        @Stable
        val DateArrow = IndiStrawIcon(
            drawableId = R.drawable.ic_date_arrow,
            contentDescription = "date arrow"
        )

        @Stable
        val FastSearch = IndiStrawIcon(
            drawableId = R.drawable.ic_fast_search,
            contentDescription = "fast search"
        )

        @Stable
        val Check = IndiStrawIcon(
            drawableId = R.drawable.ic_check,
            contentDescription = "check"
        )

        @Stable
        val UnCheck = IndiStrawIcon(
            drawableId = R.drawable.ic_uncheck,
            contentDescription = "un check"
        )

        @Stable
        val Gallery = IndiStrawIcon(
            drawableId = R.drawable.ic_gallery,
            contentDescription = "gallery"
        )

        @Stable
        val Camera = IndiStrawIcon(
            drawableId = R.drawable.ic_camera,
            contentDescription = "camera"
        )

        @Stable
        val Search = IndiStrawIcon(
            drawableId = R.drawable.ic_search,
            contentDescription = "search"
        )

        @Stable
        val Profile = IndiStrawIcon(
            drawableId = R.drawable.ic_profile,
            contentDescription = "profile"
        )

        @Stable
        val NoImage = IndiStrawIcon(
            drawableId = R.drawable.ic_no_img,
            contentDescription = "no image"
        )
    }
}