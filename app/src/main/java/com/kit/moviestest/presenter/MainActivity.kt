package com.kit.moviestest.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kit.moviestest.data.navigation.AppNavigation
import com.kit.moviestest.data.navigation.AppNavigation.Companion.PICKED_CARD_ID_KEY
import com.kit.moviestest.presenter.detail.DetailScreen
import com.kit.moviestest.presenter.movies.MoviesScreen
import com.kit.moviestest.presenter.ui.theme.MoviesTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesTestTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = AppNavigation.Movies.destination
                ) {
                    composable(AppNavigation.Movies.destination) {
                        MoviesScreen {
                            navController.navigate(AppNavigation.Detail.destination + "/$it")
                        }
                    }

                    composable(
                        route = AppNavigation.Detail.destination + "/{$PICKED_CARD_ID_KEY}",
                        arguments = listOf(
                            navArgument(name = PICKED_CARD_ID_KEY) {
                                type = NavType.IntType
                            })
                    ) { backStackEntry ->
                        DetailScreen(backStackEntry.arguments?.getInt(PICKED_CARD_ID_KEY)) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}