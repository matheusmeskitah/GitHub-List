package com.meskitah.githublist.data.mapper

import com.meskitah.githublist.data.remote.dto.OwnerDTO
import com.meskitah.githublist.data.remote.dto.RepositoryDTO
import com.meskitah.githublist.domain.model.Owner
import com.meskitah.githublist.domain.model.Repository

fun RepositoryDTO.toRepository(): Repository? {
    return id?.let { id ->
        owner?.toOwner()?.let { owner ->
            Repository(
                id = id,
                name = name ?: "",
                description = description ?: "",
                owner = owner,
                forks = forks ?: 0,
                stargazersCount = stargazers_count ?: 0
            )
        }
    }
}

fun OwnerDTO.toOwner(): Owner? {
    return id?.let {
        Owner(
            id = it,
            login = login ?: "",
            avatarUrl = avatar_url ?: ""
        )
    }
}