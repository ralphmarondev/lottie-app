package com.maronworks.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maronworks.myapplication.details.DetailsScreen
import com.maronworks.myapplication.home.HomeScreen
import com.maronworks.myapplication.navigation.screen.Screen
import com.maronworks.myapplication.profile.ProfileScreen
import com.maronworks.myapplication.splash.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                afterSplash = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                },
                onGetClick = {
                    navController.navigate(Screen.Details.route)
                }
            )
        }
        composable(Screen.Details.route) {
            DetailsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}