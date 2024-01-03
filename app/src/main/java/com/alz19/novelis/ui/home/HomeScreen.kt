package com.alz19.novelis.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alz19.novelis.ui.home.components.CardStoryItem
import com.alz19.novelis.ui.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    onEvent: (HomeEvent) -> Unit,
    state: HomeState
) {
    Scaffold (
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp),
                title = {
                    Row {
                        Text(
                            text = "Nov",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = "Elis",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.BookmarkScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Book,
                            contentDescription = "Bookmark Page"
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(Screen.AboutScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "About page"
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.search,
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search icon") },
                    onValueChange = { text ->
                        onEvent(HomeEvent.SearchStory(text))
                    },
                    label = {
                        Text(text = "Search")
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn {
                    items(items = state.stories, key = { it.id!! }
                    ) { story ->
                        CardStoryItem(
                            modifier= Modifier
                                .clickable {
                                    navController.navigate(Screen.DetailScreen.route + "/id=${story.id}")
                                },
                            story = story,
                        )
                        Divider()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        state = HomeState(),
        onEvent = {}
    )
}