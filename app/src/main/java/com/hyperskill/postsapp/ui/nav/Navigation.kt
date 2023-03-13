package com.hyperskill.postsapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hyperskill.postsapp.ui.detail.DetailScreen
import com.hyperskill.postsapp.ui.home.HomeScreen
import com.hyperskill.postsapp.ui.home.HomeViewModel
import com.hyperskill.postsapp.utils.Constants.Companion.KEY_POST_ID
import com.hyperskill.postsapp.utils.Constants.Screens.DETAIL_SCREEN
import com.hyperskill.postsapp.utils.Constants.Screens.HOME_SCREEN

sealed class Screens (val route: String) {

    object Home: Screens(route = HOME_SCREEN)
    object Detail: Screens(route = DETAIL_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, homeViewModel: HomeViewModel) {
    NavHost(navController = navController, startDestination =  Screens.Home.route) {

        composable(route = Screens.Home.route) {
            HomeScreen(homeViewModel = homeViewModel, navController = navController)
        }

        composable(route = Screens.Detail.route + "/{$KEY_POST_ID}") { backStackEntry ->
            DetailScreen(
                id = backStackEntry.arguments?.getString(KEY_POST_ID) ?: "1",
                homeViewModel = homeViewModel,
                navController = navController
            )
        }

    }
}