package com.danbam.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danbam.design_system.IndiStrawTheme
import com.danbam.presentation.util.AppNavigationItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            IndiStrawTheme {
                BaseApp(navController = navController)
            }
        }
    }
}

@Composable
fun BaseApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppNavigationItem.Login.route) {
        composable(route = AppNavigationItem.Login.route) {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    IndiStrawTheme {
        BaseApp(navController = navController)
    }
}