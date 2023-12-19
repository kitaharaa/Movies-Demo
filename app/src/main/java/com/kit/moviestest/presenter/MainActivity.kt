package com.kit.moviestest.presenter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kit.moviestest.data.navigation.AppNavigation
import com.kit.moviestest.data.room.dao.MovieDao
import com.kit.moviestest.presenter.detail.DetailScreen
import com.kit.moviestest.presenter.movies.MoviesScreen
import com.kit.moviestest.presenter.ui.theme.MoviesTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dao: MovieDao

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
                            //todo pass id in argument
                        }
                    }

                    composable(AppNavigation.Detail.destination) {
                        DetailScreen()
                    }
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            dao.getAllMovies().collect {
                Log.e("DatabaseData", it.toString())
            }
        }
    }
}