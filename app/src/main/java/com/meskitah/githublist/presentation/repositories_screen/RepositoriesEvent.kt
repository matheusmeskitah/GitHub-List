package com.meskitah.githublist.presentation.repositories_screen

import com.meskitah.githublist.domain.model.Repository

sealed class RepositoriesEvent {
    data object OnLoadSports : RepositoriesEvent()
    data object OnReloadSports : RepositoriesEvent()
    data class OnSportsClick(val sport: Repository) : RepositoriesEvent()
}