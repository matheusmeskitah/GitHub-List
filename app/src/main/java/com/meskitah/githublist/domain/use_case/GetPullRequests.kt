package com.meskitah.githublist.domain.use_case

import android.content.Context
import com.meskitah.githublist.domain.model.PullRequest
import com.meskitah.githublist.domain.repository.GitHubRepository

class GetPullRequests(private val repository: GitHubRepository) {

    suspend operator fun invoke(
        user: String,
        repositoryName: String,
        context: Context
    ): Result<List<PullRequest>> {
        return repository.getPullRequests(user, repositoryName, context)
    }
}