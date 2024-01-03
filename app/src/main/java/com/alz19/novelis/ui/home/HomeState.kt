package com.alz19.novelis.ui.home

import com.alz19.novelis.model.Story


data class HomeState(
    val stories: List<Story> = emptyList(),
    val search: String = ""
)