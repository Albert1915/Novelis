package com.alz19.novelis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alz19.novelis.ui.about.AboutScreen
import com.alz19.novelis.ui.bookmark.BookmarkScreen
import com.alz19.novelis.ui.bookmark.BookmarkViewModel
import com.alz19.novelis.ui.detail.DetailScreen
import com.alz19.novelis.ui.detail.DetailViewModel
import com.alz19.novelis.ui.home.HomeScreen
import com.alz19.novelis.ui.home.HomeViewModel
import com.alz19.novelis.ui.theme.NovelisTheme
import com.alz19.novelis.ui.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NovelisTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            val viewModel = hiltViewModel<HomeViewModel>()
                            val state by viewModel.state.collectAsState()
                            HomeScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.AboutScreen.route
                        ) {
                            AboutScreen(navController = navController)
                        }
                        composable(route = Screen.BookmarkScreen.route){
                            val viewModel = hiltViewModel<BookmarkViewModel>()
                            val state by viewModel.state.collectAsState()
                            BookmarkScreen(navController = navController, state = state)
                        }

                        composable(
                            route = Screen.DetailScreen.route + "/id={id}",
                            arguments = listOf(
                                navArgument(
                                    name = "id"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val viewModel = hiltViewModel<DetailViewModel>()
                            val state by viewModel.state.collectAsState()
                            DetailScreen(
                                navController = navController,
                                state = state,
                                onEvent = viewModel::onEvent
                            )
                        }
                    }
                }
            }
        }
    }
}
