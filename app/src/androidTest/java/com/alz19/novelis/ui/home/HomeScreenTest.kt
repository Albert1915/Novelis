package com.alz19.novelis.ui.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.alz19.novelis.R
import com.alz19.novelis.model.Story
import com.alz19.novelis.ui.detail.DetailScreen
import com.alz19.novelis.ui.detail.DetailState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        val storyList = listOf(
            Story(
                id = 3,
                title = "Where Stories Begin",
                genre = "Romance",
                author = "Stanza Alquisha, Maria Perdana, Robin Wijaya, Arata Kim, Kanigara, Meera, Nureesh Vhalega, Ratifa Mazari, Tian Topandi, dan Zaidatul Uyun Akrami",
                synopsis = "Where Stories Begin adalah antologi cerpen hasil kurasi Redaksi Novel Elex Media dari perlombaan yang diadakan oleh Wacaku. Where Stories Begin menyuguhkan cerita pendek dari sepuluh penulis yang terpilih dari perlombaan yang diadakan pada 2022 lalu. Cerita-cerita karya Stanza Alquisha, Maria Perdana, Robin Wijaya, Arata Kim, Kanigara, Meera, Nureesh Vhalega, Ratifa Mazari, Tian Topandi, dan Zaidatul Uyun Akrami mengisahkan bahwa perkara cinta tak melulu soal kebahagiaan. Bahwa cinta tak selalu semanis gulali, dan indah seperti gumpalan awan merah muda. Because these are where stories begin…",
                image = R.drawable.where_story_begins
            ),
        )

        composeTestRule.setContent {
            val navController = rememberNavController()
            DetailScreen(
                navController = navController,
                state = DetailState(
                    story = storyList[0]
                ),
                onEvent = {}
            )
        }
    }

    @Test
    fun testNavigationToDetailScreen() {
        composeTestRule.onNodeWithText("Where Stories Begin", ignoreCase = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithText("Where Stories Begin", ignoreCase = true)
            .assertIsDisplayed()
    }
}