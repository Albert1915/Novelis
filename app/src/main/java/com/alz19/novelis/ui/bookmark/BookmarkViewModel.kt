package com.alz19.novelis.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alz19.novelis.data.simple_case.StoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val storyUseCases: StoryUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarkState())
    val state = _state.asStateFlow()

    private var getStoryBookmarkJob: Job? = null

    init {
        getStoryBookmark()
    }

    private fun getStoryBookmark() {
        getStoryBookmarkJob?.cancel()
        getStoryBookmarkJob = storyUseCases.getAllStoryBookmark()
            .onEach { stories ->
                _state.value = _state.value.copy(
                    stories = stories
                )
            }.launchIn(viewModelScope)
    }

}