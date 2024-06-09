package com.meskitah.githublist.presentation.repositories_screen

import androidx.navigation.NavController
import com.meskitah.githublist.domain.model.Repository

sealed class RepositoriesEvent {
    data object OnLoadRepositories : RepositoriesEvent()
    data class OnRepositoryClick(
        val repository: Repository,
        val navController: NavController
    ) : RepositoriesEvent()
}