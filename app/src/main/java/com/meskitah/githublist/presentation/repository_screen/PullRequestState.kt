package com.meskitah.githublist.presentation.repository_screen

import com.meskitah.githublist.domain.model.PullRequest

data class PullRequestState(
    val pullRequests: MutableList<PullRequest> = mutableListOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
