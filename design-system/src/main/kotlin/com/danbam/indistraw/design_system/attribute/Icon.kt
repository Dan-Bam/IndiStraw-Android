package com.danbam.indistraw.design_system.attribute

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
import com.danbam.indistraw.design_system.R
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
        val Logo = IndiStrawIconList(
            drawableId = R.drawable.ic_logo,
            contentDescription = "logo"
        )
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
        val DownArrow = IndiStrawIconList(
            drawableId = R.drawable.ic_down_arrow,
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
        val PlayFirst = IndiStrawIconList(
            drawableId = R.drawable.ic_play_first,
            contentDescription = "playFirst"
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

        @Stable
        val Minus = IndiStrawIconList(
            drawableId = R.drawable.ic_minus,
            contentDescription = "minus"
        )

        @Stable
        val People = IndiStrawIconList(
            drawableId = R.drawable.ic_people,
            contentDescription = "people"
        )

        @Stable
        val Movie = IndiStrawIconList(
            drawableId = R.drawable.ic_movie,
            contentDescription = "movie"
        )

        @Stable
        val BankGwangju = IndiStrawIconList(
            drawableId = R.drawable.ic_bank_gwang,
            contentDescription = "gwangjuBank"
        )

        @Stable
        val BankHana = IndiStrawIconList(
            drawableId = R.drawable.ic_bank_hana,
            contentDescription = "hanaBank"
        )

        @Stable
        val BankKakao = IndiStrawIconList(
            drawableId = R.drawable.ic_bank_kakao,
            contentDescription = "kakaoBank"
        )

        @Stable
        val BankNh = IndiStrawIconList(
            drawableId = R.drawable.ic_bank_nh,
            contentDescription = "nhBank"
        )

        @Stable
        val BankPost = IndiStrawIconList(
            drawableId = R.drawable.ic_bank_post,
            contentDescription = "postBank"
        )

        @Stable
        val BankShinhyeop = IndiStrawIconList(
            drawableId = R.drawable.ic_bank_shinhyeop,
            contentDescription = "shinhyeopBank"
        )

        @Stable
        val BankShinhan = IndiStrawIconList(
            drawableId = R.drawable.ic_bank_shinhan,
            contentDescription = "shinhanBank"
        )

        @Stable
        val BankToss = IndiStrawIconList(
            drawableId = R.drawable.ic_bank_toss,
            contentDescription = "tossBank"
        )

        @Stable
        val BankWoori = IndiStrawIconList(
            drawableId = R.drawable.ic_bank_woori,
            contentDescription = "wooriBank"
        )

        @Stable
        val PlayerPlay = IndiStrawIconList(
            drawableId = R.drawable.ic_player_play,
            contentDescription = "playerPlay"
        )

        @Stable
        val PlayerStop = IndiStrawIconList(
            drawableId = R.drawable.ic_player_stop,
            contentDescription = "playerStop"
        )

        @Stable
        val PlayerBack = IndiStrawIconList(
            drawableId = R.drawable.ic_player_back,
            contentDescription = "playerBack"
        )

        @Stable
        val PlayerForward = IndiStrawIconList(
            drawableId = R.drawable.ic_player_forward,
            contentDescription = "playerForward"
        )

        @Stable
        val PlayerPIP = IndiStrawIconList(
            drawableId = R.drawable.ic_player_pip,
            contentDescription = "playerPIP"
        )

        @Stable
        val PlayerFinish = IndiStrawIconList(
            drawableId = R.drawable.ic_player_finish,
            contentDescription = "playerFinish"
        )

        @Stable
        val PlayerLockOpen = IndiStrawIconList(
            drawableId = R.drawable.ic_player_lock_open,
            contentDescription = "playerLockOpen"
        )

        @Stable
        val PlayerLockClose = IndiStrawIconList(
            drawableId = R.drawable.ic_player_lock_close,
            contentDescription = "playerLockClose"
        )
    }
}