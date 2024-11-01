package com.meskitah.githublist.presentation.navigation

import androidx.navigation.NavController
import com.meskitah.githublist.domain.model.Repository

sealed class NavigationEvents {
    data object OnNavigateUp : NavigationEvents()
    data class OnNavigateToPullRequests(val repository: Repository) : NavigationEvents()

    companion object {
        fun onEvent(navController: NavController, event: NavigationEvents) {
            when (event) {
                OnNavigateUp -> navController.navigateUp()
                is OnNavigateToPullRequests -> {
                    navController.navigate(
                        ScreenPullRequests(
                            creator = event.repository.owner.login,
                            repositoryName = event.repository.name
                        )
                    )
                }
            }
        }
    }
}