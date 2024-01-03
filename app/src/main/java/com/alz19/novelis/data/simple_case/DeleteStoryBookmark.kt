package com.alz19.novelis.data.simple_case

import com.alz19.novelis.model.repository.StoryRepository
import com.alz19.novelis.model.Story


class DeleteStoryBookmark(
    private val repository: StoryRepository
) {
    suspend operator fun invoke(story: Story) {
        repository.deleteStoryBookmark(story)
    }
}