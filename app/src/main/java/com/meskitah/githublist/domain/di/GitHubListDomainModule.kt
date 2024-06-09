package com.meskitah.githublist.domain.di

import com.meskitah.githublist.domain.repository.GitHubRepository
import com.meskitah.githublist.domain.use_case.GetPullRequests
import com.meskitah.githublist.domain.use_case.GetRepositories
import com.meskitah.githublist.domain.use_case.GitHubUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object GitHubListDomainModule {

    @Provides
    @ViewModelScoped
    fun provideSportsUseCases(
        repository: GitHubRepository
    ): GitHubUseCases {
        return GitHubUseCases(
            getRepositories = GetRepositories(repository),
            getPullRequests = GetPullRequests(repository)
        )
    }
}