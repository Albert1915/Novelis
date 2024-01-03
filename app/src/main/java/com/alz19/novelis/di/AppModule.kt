package com.alz19.novelis.di

import android.app.Application
import androidx.room.Room
import com.alz19.novelis.data.local.StoryDatabase
import com.alz19.novelis.data.simple_case.AddStoryBookmark
import com.alz19.novelis.data.simple_case.DeleteStoryBookmark
import com.alz19.novelis.data.simple_case.GetAllStory
import com.alz19.novelis.data.simple_case.GetAllStoryBookmark
import com.alz19.novelis.data.simple_case.GetAllStoryBookmarkById
import com.alz19.novelis.data.simple_case.GetStory
import com.alz19.novelis.data.simple_case.StoryUseCases
import com.alz19.novelis.model.repository.StoryRepository
import com.alz19.novelis.model.repository.StoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTodoDatabase(app : Application) : StoryDatabase {
        return Room.databaseBuilder(
            app,
            StoryDatabase::class.java,
            StoryDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesTodoRepository(db : StoryDatabase) : StoryRepository {
        return StoryRepositoryImpl(db.storyDao)
    }

    @Provides
    @Singleton
    fun providesTodoUseCases (repository: StoryRepository) : StoryUseCases {
        return StoryUseCases(
            getAllStory = GetAllStory(),
            getAllStoryBookmark = GetAllStoryBookmark(repository),
            addStoryBookmark = AddStoryBookmark(repository),
            deleteStoryBookmark = DeleteStoryBookmark(repository),
            getStory = GetStory(),
            getAllStoryBookmarkById = GetAllStoryBookmarkById(repository)
        )
    }
}