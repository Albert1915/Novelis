package com.alz19.novelis.data.simple_case

import com.alz19.novelis.model.repository.StoryRepository
import com.alz19.novelis.model.Story


class GetAllStoryBookmarkById(
    private val repository: StoryRepository
) {

    suspend operator fun invoke(id: Int): Story? {
        return repository.getStoryBookmarkById(id)
    }

}