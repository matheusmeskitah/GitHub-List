package com.meskitah.githublist.data.remote.dto

data class GitHubDTO(
    val incomplete_results: Boolean?,
    val items: List<RepositoryDTO>?,
    val total_count: Int?
)