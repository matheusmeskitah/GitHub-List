package com.meskitah.githublist.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.meskitah.githublist.presentation.repositories_screen.RepositoryScreen

fun NavGraphBuilder.addGitHubListGraph(
    navController: NavController,
    snackbarState: SnackbarHostState
) {
    composable<ScreenRepositoryList> {
        RepositoryScreen(
            snackbarHostState = snackbarState,
            navController = navController
        )
    }

    composable<ScreenRepositoryDetails> {
        val args = it.toRoute<ScreenRepositoryDetails>()
    }
}