package com.meskitah.githublist.domain.di

import com.meskitah.githublist.domain.repository.GitHubListRepository
import com.meskitah.githublist.domain.use_case.GetRepositories
import com.meskitah.githublist.domain.use_case.RepositoriesUseCases
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
        repository: GitHubListRepository
    ): RepositoriesUseCases {
        return RepositoriesUseCases(
            getRepositories = GetRepositories(repository)
        )
    }
}