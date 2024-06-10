package com.meskitah.githublist.presentation.repository_screen

import androidx.navigation.NavController

sealed class PullRequestEvent {
    data class OnLoadPullRequest(
        val user: String,
        val repositoryName: String
    ) : PullRequestEvent()

    data class OnReloadPullRequest(
        val user: String,
        val repositoryName: String
    ) : PullRequestEvent()

    data class OnNavigateUp(val navController: NavController) : PullRequestEvent()
}