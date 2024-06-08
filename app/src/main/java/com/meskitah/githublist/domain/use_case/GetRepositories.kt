package com.meskitah.githublist.domain.use_case

import androidx.paging.PagingData
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.domain.repository.GitHubListRepository
import kotlinx.coroutines.flow.Flow

class GetRepositories(private val repository: GitHubListRepository) {

    suspend operator fun invoke(): Flow<PagingData<Repository>> {
        return repository.getRepos()
    }
}