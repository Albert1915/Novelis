package com.alz19.novelis.data.simple_case


data class StoryUseCases (
    val getAllStory: GetAllStory,
    val getAllStoryBookmark: GetAllStoryBookmark,
    val deleteStoryBookmark: DeleteStoryBookmark,
    val addStoryBookmark: AddStoryBookmark,
    val getStory: GetStory,
    val getAllStoryBookmarkById: GetAllStoryBookmarkById
)