package com.danbam.design_system.attribute

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.danbam.design_system.R
import javax.annotation.concurrent.Immutable

@Composable
fun IndiStrawIcon(
    modifier: Modifier = Modifier,
    icon: IndiStrawIconList,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = icon.drawableId),
        contentDescription = icon.contentDescription,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}

@Immutable
class IndiStrawIconList private constructor(
    @DrawableRes val drawableId: Int,
    val contentDescription: String? = null,
) {
    companion object {
        @Stable
        val OpenEyes = IndiStrawIconList(
            drawableId = R.drawable.ic_open_eyes,
            contentDescription = "open eyes"
        )

        @Stable
        val Back = IndiStrawIconList(
            drawableId = R.drawable.ic_back,
            contentDescription = "back"
        )

        @Stable
        val Down = IndiStrawIconList(
            drawableId = R.drawable.ic_down,
            contentDescription = "down"
        )

        @Stable
        val DateArrow = IndiStrawIconList(
            drawableId = R.drawable.ic_date_arrow,
            contentDescription = "date arrow"
        )

        @Stable
        val FastSearch = IndiStrawIconList(
            drawableId = R.drawable.ic_fast_search,
            contentDescription = "fast search"
        )

        @Stable
        val Check = IndiStrawIconList(
            drawableId = R.drawable.ic_check,
            contentDescription = "check"
        )

        @Stable
        val UnCheck = IndiStrawIconList(
            drawableId = R.drawable.ic_uncheck,
            contentDescription = "un check"
        )

        @Stable
        val Gallery = IndiStrawIconList(
            drawableId = R.drawable.ic_gallery,
            contentDescription = "gallery"
        )

        @Stable
        val Camera = IndiStrawIconList(
            drawableId = R.drawable.ic_camera,
            contentDescription = "camera"
        )

        @Stable
        val Search = IndiStrawIconList(
            drawableId = R.drawable.ic_search,
            contentDescription = "search"
        )

        @Stable
        val Profile = IndiStrawIconList(
            drawableId = R.drawable.ic_profile,
            contentDescription = "profile"
        )

        @Stable
        val NoImage = IndiStrawIconList(
            drawableId = R.drawable.ic_no_img,
            contentDescription = "no image"
        )

        @Stable
        val Plus = IndiStrawIconList(
            drawableId = R.drawable.ic_plus,
            contentDescription = "plus"
        )

        @Stable
        val PlusCircle = IndiStrawIconList(
            drawableId = R.drawable.ic_plus_circle,
            contentDescription = "plus circle"
        )

        @Stable
        val Setting = IndiStrawIconList(
            drawableId = R.drawable.ic_setting,
            contentDescription = "setting"
        )

        @Stable
        val Play = IndiStrawIconList(
            drawableId = R.drawable.ic_play,
            contentDescription = "play"
        )

        @Stable
        val Shield = IndiStrawIconList(
            drawableId = R.drawable.ic_shield,
            contentDescription = "shield"
        )

        @Stable
        val Earth = IndiStrawIconList(
            drawableId = R.drawable.ic_earth,
            contentDescription = "earth"
        )

        @Stable
        val QR = IndiStrawIconList(
            drawableId = R.drawable.ic_qr,
            contentDescription = "qr"
        )

        @Stable
        val QRGrid = IndiStrawIconList(
            drawableId = R.drawable.ic_qr_grid,
            contentDescription = "qrGrid"
        )

        @Stable
        val Attached = IndiStrawIconList(
            drawableId = R.drawable.ic_attaced,
            contentDescription = "attaced"
        )

        @Stable
        val NavSearch = IndiStrawIconList(
            drawableId = R.drawable.ic_nav_search,
            contentDescription = "navSearch"
        )

        @Stable
        val NavHome = IndiStrawIconList(
            drawableId = R.drawable.ic_nav_home,
            contentDescription = "navHome"
        )

        @Stable
        val NavMovie = IndiStrawIconList(
            drawableId = R.drawable.ic_nav_movie,
            contentDescription = "navMovie"
        )

        @Stable
        val NavSetting = IndiStrawIconList(
            drawableId = R.drawable.ic_nav_setting,
            contentDescription = "navSetting"
        )

        @Stable
        val FastPlay = IndiStrawIconList(
            drawableId = R.drawable.ic_fast_play,
            contentDescription = "fastPlay"
        )

        @Stable
        val Delete = IndiStrawIconList(
            drawableId = R.drawable.ic_delete,
            contentDescription = "delete"
        )
    }
}