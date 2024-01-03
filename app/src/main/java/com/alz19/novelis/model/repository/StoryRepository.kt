package com.alz19.novelis.model.repository

import com.alz19.novelis.model.Story
import kotlinx.coroutines.flow.Flow

interface StoryRepository {

    fun getAllStoryBookmark () : Flow<List<Story>>

    suspend fun insertStoryBookmark(story: Story)

    suspend fun deleteStoryBookmark(story: Story)

    suspend fun getStoryBookmarkById (id : Int) : Story?
}