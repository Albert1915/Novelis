package com.alz19.novelis.data.simple_case

import com.alz19.novelis.model.repository.StoryRepository
import com.alz19.novelis.model.Story
import kotlinx.coroutines.flow.Flow

class GetAllStoryBookmark(
    private val repository: StoryRepository
) {
    operator fun invoke(): Flow<List<Story>> {
        return repository.getAllStoryBookmark()
    }
}