package com.danbam.design_system.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.Surface
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList

sealed class TvNavigationItem(val route: String, val icon: IndiStrawIconList, val titleId: Int) {
    object Search : TvNavigationItem("search", IndiStrawIconList.NavSearch, R.string.search)
    object Home : TvNavigationItem("home", IndiStrawIconList.NavHome, R.string.home)
    object Movie : TvNavigationItem("movie", IndiStrawIconList.NavMovie, R.string.movie_all)
    object Setting : TvNavigationItem("setting", IndiStrawIconList.NavSetting, R.string.setting)
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun IndiStrawTvNavigationDrawer(
    content: @Composable () -> Unit,
    currentMenu: TvNavigationItem,
    onMenuSelected: (TvNavigationItem) -> Unit
) {
    ModalNavigationDrawer(drawerContent = { drawer ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(if (drawer == DrawerValue.Open) IndiStrawTheme.colors.lightBlack else IndiStrawTheme.colors.black),
            verticalArrangement = Arrangement.Center
        ) {
            listOf(
                TvNavigationItem.Search,
                TvNavigationItem.Home,
                TvNavigationItem.Movie,
                TvNavigationItem.Setting
            ).forEach {
                navigationRow(drawer, it, currentMenu, Modifier, onMenuSelected)
                Spacer(modifier = Modifier.height(25.dp))
            }
        }
    }, content = content)
}

@OptIn(ExperimentalTvMaterial3Api::class)
private val navigationRow: @Composable (
    drawerValue: DrawerValue,
    menu: TvNavigationItem,
    currentMenu: TvNavigationItem,
    modifier: Modifier,
    onMenuSelected: (TvNavigationItem) -> Unit
) -> Unit = { drawerValue, menu, currentMenu, modifier, onMenuSelected ->
    val focusRequester = remember { FocusRequester() }
    if (drawerValue == DrawerValue.Open && menu == currentMenu) {
        SideEffect {
            focusRequester.requestFocus()
        }
    }

    Surface(
        modifier = Modifier
            .focusRequester(focusRequester),
        scale = ClickableSurfaceDefaults.scale(
            focusedScale = 1F
        ),
        color = ClickableSurfaceDefaults.color(
            color = Color.Transparent,
            focusedColor = Color.Transparent,
            pressedColor = Color.Transparent,
            disabledColor = Color.Transparent
        ),
        border = ClickableSurfaceDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(4.dp, IndiStrawTheme.colors.main),
                shape = RoundedCornerShape(0.dp)
            )
        ),
        onClick = { onMenuSelected(menu) }
    ) {
        Column {
            Row(
                modifier = modifier
                    .padding(horizontal = 30.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IndiStrawIcon(icon = menu.icon)
                if (drawerValue == DrawerValue.Open) {
                    TitleRegular(
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxWidth(0.15F),
                        text = stringResource(id = menu.titleId),
                        fontSize = 31
                    )
                }
            }
            if (drawerValue != DrawerValue.Open && currentMenu == menu) {
                Spacer(modifier = Modifier.height(3.dp))
                Box(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .width(20.dp)
                        .height(3.dp)
                        .background(
                            color = IndiStrawTheme.colors.main,
                            shape = IndiStrawTheme.shapes.smallRounded
                        )
                )
            }
        }
    }
}