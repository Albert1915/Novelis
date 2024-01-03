package com.alz19.novelis.data.simple_case


import com.alz19.novelis.data.dummy.storyList
import com.alz19.novelis.model.Story
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class GetAllStory {
    operator fun invoke(
        title :String = ""
    ) : Flow<List<Story>> {
        return MutableStateFlow(
            if (title.isEmpty()){
                storyList
            }else{
                storyList.filter { it.title.lowercase().contains(title.lowercase()) }
            }
        )
    }
}