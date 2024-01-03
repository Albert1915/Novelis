package com.alz19.novelis.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alz19.novelis.data.simple_case.StoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val storyUseCases: StoryUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<Int>("id")?.let { storyId ->
                if (storyId != -1) {
                    storyUseCases.getStory(storyId).also { story ->
                        _state.value = _state.value.copy(
                            story = story
                        )
                    }
                }
                if (storyUseCases.getAllStoryBookmarkById(storyId) != null) {
                    _state.value = _state.value.copy(
                        story = _state.value.story?.copy(
                            isBookmark = true
                        )
                    )
                }
            }
        }
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.OnToggleFavorite -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        story = _state.value.story!!.copy(
                            isBookmark = !_state.value.story!!.isBookmark
                        )
                    )
                    if (_state.value.story!!.isBookmark) {
                        storyUseCases.addStoryBookmark(
                            _state.value.story!!
                        )
                    } else {
                        storyUseCases.deleteStoryBookmark(
                            _state.value.story!!
                        )
                    }
                }
            }
        }
    }
}