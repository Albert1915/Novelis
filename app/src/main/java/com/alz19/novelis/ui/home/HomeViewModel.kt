package com.alz19.novelis.ui.home

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
class HomeViewModel @Inject constructor(
    private val storyUseCases: StoryUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private var getStoryJob: Job? = null

    init {
        getStory("")
    }

    fun onEvent (event: HomeEvent){
        when(event){
            is HomeEvent.SearchStory -> {
                _state.value = _state.value.copy(
                    search = event.title
                )
                getStory(event.title)
            }
        }
    }

    private fun getStory (title : String){
        getStoryJob?.cancel()
        getStoryJob = storyUseCases.getAllStory(title)
            .onEach {story ->
                _state.value = _state.value.copy(
                    stories = story
                )
            }.launchIn(viewModelScope)
    }
}