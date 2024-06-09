package com.meskitah.githublist.core.util

sealed class UiEvent {
    data object Success : UiEvent()
}