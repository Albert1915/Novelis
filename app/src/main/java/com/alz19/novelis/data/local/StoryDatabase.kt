package com.alz19.novelis.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alz19.novelis.model.Story


@Database(
    version = 1,
    entities = [Story::class]
)
abstract class StoryDatabase  : RoomDatabase(){

    abstract val storyDao: StoryDao

    companion object {
        const val DATABASE_NAME = "story_db"
    }
}