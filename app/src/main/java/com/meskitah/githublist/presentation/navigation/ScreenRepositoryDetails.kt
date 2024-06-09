package com.meskitah.githublist.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data class ScreenRepositoryDetails(
    val creator: String,
    val repositoryName: String
)