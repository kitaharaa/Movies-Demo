package com.kit.moviestest.data.navigation

sealed class AppNavigation(val destination: String) {
    data object Movies : AppNavigation(destination = "movies")
    data object Detail : AppNavigation(destination = "detail")
}