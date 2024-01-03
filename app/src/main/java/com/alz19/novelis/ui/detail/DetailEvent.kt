package com.alz19.novelis.ui.detail

sealed class DetailEvent {
    data object OnToggleFavorite : DetailEvent()
}