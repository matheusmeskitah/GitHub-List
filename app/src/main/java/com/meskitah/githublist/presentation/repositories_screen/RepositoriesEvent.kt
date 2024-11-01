package com.meskitah.githublist.presentation.repositories_screen

sealed class RepositoriesEvent {
    data object OnLoadRepositories : RepositoriesEvent()
}