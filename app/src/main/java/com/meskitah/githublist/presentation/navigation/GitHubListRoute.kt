package com.meskitah.githublist.presentation.navigation

sealed class GitHubListRoute(val route: String) {
    data object RepositoryListRoute: GitHubListRoute("repositories")
}