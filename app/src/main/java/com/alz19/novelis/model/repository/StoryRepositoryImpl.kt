package com.alz19.novelis.model.repository

import com.alz19.novelis.data.local.StoryDao
import com.alz19.novelis.model.Story
import kotlinx.coroutines.flow.Flow

class StoryRepositoryImpl(
    private val storyDao: StoryDao
) : StoryRepository {
    override fun getAllStoryBookmark(): Flow<List<Story>> {
        return storyDao.getAllStoryBookmark()
    }

    override suspend fun insertStoryBookmark(story: Story) {
        storyDao.insertStoryBookmark(story)
    }

    override suspend fun deleteStoryBookmark(story: Story) {
        storyDao.deleteStoryBookmark(story)
    }

    override suspend fun getStoryBookmarkById(id: Int) : Story? {
        return storyDao.getAllStoryBookmarkById(id)
    }

}