package com.alz19.novelis.ui.detail

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alz19.novelis.data.dummy.storyList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    state: DetailState,
    onEvent: (DetailEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp),
                title = {
                    Row {
                        Text(
                            text = "Story ",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Details",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIos,
                            contentDescription = "Back Topbar"
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(DetailEvent.OnToggleFavorite)
            }) {
                Icon(
                    imageVector = if (state.story?.isBookmark!!) {
                        Icons.Default.Bookmark
                    } else {
                        Icons.Default.BookmarkBorder
                    },
                    contentDescription = "Bookmark icons"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .heightIn(max = 1000.dp)
        ) {
            item {
                // Header
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .height(300.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .height(300.dp)
                            .padding(vertical = 15.dp),
                        painter = painterResource(id = state.story!!.image),
                        contentDescription = "Detail image story"
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 15.dp)
                            .align(Alignment.Top)
                    ) {
                        Text(
                            text = state.story.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = state.story.genre,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Author: ${state.story.author}",
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Synopsis",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 8.dp)
                )
            }
            item {
                // Main content
                state.story?.let {
                    Text(
                        text = it.synopsis,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)

                    )
                }
                Spacer(modifier = Modifier.height(200.dp)) // Spacer for scrolling below
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        navController = rememberNavController(),
        state = DetailState(
            story = storyList[0],
        ),
        onEvent = {}
    )
}