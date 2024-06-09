package com.meskitah.githublist.data.remote.dto

data class PullRequestDTO(
    val body: String?,
    val created_at: String?,
    val title: String?,
    val user: UserPRDTO
)