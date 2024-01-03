package com.alz19.novelis.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alz19.novelis.model.Story
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {

    @Query("SELECT * FROM Story")
    fun getAllStoryBookmark() : Flow<List<Story>>

    @Query("SELECT * FROM Story WHERE id = :id")
    suspend fun getAllStoryBookmarkById(id:Int) : Story?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStoryBookmark (story: Story)

    @Delete
    suspend fun deleteStoryBookmark(story: Story)
}