package com.alz19.novelis.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Story (
    @PrimaryKey
    val id: Int?,
    val title: String,
    val genre: String,
    val author: String,
    val synopsis: String,
    val image: Int,
    val isBookmark: Boolean = false
)