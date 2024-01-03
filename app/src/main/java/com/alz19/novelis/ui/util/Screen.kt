package com.alz19.novelis.ui.util

sealed class Screen (val route : String) {
    data object HomeScreen : Screen("home")
    data object DetailScreen : Screen("detail")
    data object AboutScreen : Screen("about")
    data object BookmarkScreen : Screen("bookmark")
}