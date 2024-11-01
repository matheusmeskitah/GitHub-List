package com.meskitah.githublist.presentation.pull_requests_screen

import com.meskitah.githublist.domain.model.PullRequest

data class PullRequestState(
    val pullRequests: MutableList<PullRequest> = mutableListOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRefreshing: Boolean = false,
    val isFirstLoad: Boolean = true
)
