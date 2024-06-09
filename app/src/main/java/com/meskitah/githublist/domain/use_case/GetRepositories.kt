package com.meskitah.githublist.domain.use_case

import androidx.paging.PagingData
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow

class GetRepositories(private val repository: GitHubRepository) {

    suspend operator fun invoke(): Flow<PagingData<Repository>> {
        return repository.getRepos()
    }
}