package com.meskitah.githublist.data.remote.dto

data class RepositoryDTO(
    val id: Int?,
    val description: String?,
    val forks: Int?,
    val name: String?,
    val owner: OwnerDTO?,
    val stargazers_count: Int?
)