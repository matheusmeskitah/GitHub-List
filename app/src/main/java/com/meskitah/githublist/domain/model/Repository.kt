package com.meskitah.githublist.domain.model

data class Repository(
    val id: Int,
    val description: String,
    val forks: Int,
    val name: String,
    val owner: Owner,
    val stargazersCount: Int
)