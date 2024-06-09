package com.meskitah.githublist.domain.model

data class PullRequest(
    val id: Long,
    val body: String,
    val createdAt: String,
    val title: String,
    val user: UserPR
)