package com.maronworks.myapplication.navigation.screen

sealed class Screen (val route: String){
    data object Splash: Screen("splash")
    data object Home: Screen("home")
    data object Details: Screen("details")
    data object Profile: Screen("profile")
}