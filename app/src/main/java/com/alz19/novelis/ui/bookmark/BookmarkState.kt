package com.alz19.novelis.ui.bookmark

import com.alz19.novelis.model.Story


data class BookmarkState (
    val stories : List<Story> = emptyList()
)