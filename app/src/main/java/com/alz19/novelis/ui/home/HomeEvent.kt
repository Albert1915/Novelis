package com.alz19.novelis.ui.home

sealed class HomeEvent {
    data class SearchStory(val title : String) : HomeEvent()
}