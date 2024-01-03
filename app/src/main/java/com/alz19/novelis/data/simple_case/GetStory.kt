package com.alz19.novelis.data.simple_case

import com.alz19.novelis.data.dummy.storyList
import com.alz19.novelis.model.Story


class GetStory {
    operator fun invoke(id : Int) : Story {
        return storyList.get(id-1)
    }
}