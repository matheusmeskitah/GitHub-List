package com.meskitah.githublist.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.presentation.pull_requests_screen.PullRequestsScreen
import com.meskitah.githublist.presentation.repositories_screen.RepositoriesEvent
import com.meskitah.githublist.presentation.repositories_screen.RepositoriesViewModel
import com.meskitah.githublist.presentation.repositories_screen.RepositoryScreen

fun NavGraphBuilder.addGitHubListGraph(
    navController: NavController,
    snackbarState: SnackbarHostState
) {
    composable<ScreenRepositoryList> {
        val viewModel: RepositoriesViewModel = hiltViewModel()
        val repositories: LazyPagingItems<Repository> = viewModel.state.collectAsLazyPagingItems()
        
        RepositoryScreen(
            repositories = repositories,
            onLoadRepositories = { viewModel.onEvent(RepositoriesEvent.OnLoadRepositories) },
            onCardClick = {
                viewModel.onEvent(RepositoriesEvent.OnRepositoryClick(it, navController))
            },
            snackbarHostState = snackbarState
        )
    }

    composable<ScreenPullRequests> {
        val args = it.toRoute<ScreenPullRequests>()
        PullRequestsScreen(
            snackbarHostState = snackbarState,
            navController = navController,
            userName = args.creator,
            repositoryName = args.repositoryName
        )
    }
}