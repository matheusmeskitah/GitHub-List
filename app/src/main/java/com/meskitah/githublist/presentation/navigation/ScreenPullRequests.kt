package com.meskitah.githublist.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data class ScreenPullRequests(
    val creator: String,
    val repositoryName: String
)