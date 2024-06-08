package com.meskitah.githublist.presentation.repositories_screen

import com.meskitah.githublist.domain.model.Repository

sealed class RepositoriesEvent {
    data object OnLoadRepositories : RepositoriesEvent()
    data class OnRepositoryClick(val sport: Repository) : RepositoriesEvent()
}