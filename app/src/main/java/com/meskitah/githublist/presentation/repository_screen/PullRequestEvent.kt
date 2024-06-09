package com.meskitah.githublist.presentation.repository_screen

import android.content.Context
import androidx.navigation.NavController

sealed class PullRequestEvent {
    data class OnLoadPullRequest(
        val user: String,
        val repositoryName: String,
        val context: Context
    ) : PullRequestEvent()

    data class OnReloadPullRequest(
        val user: String,
        val repositoryName: String,
        val context: Context
    ) : PullRequestEvent()

    data class OnNavigateUp(val navController: NavController) : PullRequestEvent()
}