package com.meskitah.githublist.domain.use_case

import com.meskitah.githublist.domain.model.PullRequest
import com.meskitah.githublist.domain.repository.GitHubRepository

class GetPullRequests(private val repository: GitHubRepository) {

    suspend operator fun invoke(
        user: String,
        repositoryName: String
    ): Result<List<PullRequest>> {
        return repository.getPullRequests(user, repositoryName)
    }
}