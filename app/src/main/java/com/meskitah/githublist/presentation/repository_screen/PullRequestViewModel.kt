package com.meskitah.githublist.presentation.repository_screen

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meskitah.githublist.domain.use_case.GitHubUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PullRequestViewModel @Inject constructor(
    private val useCases: GitHubUseCases
) : ViewModel() {

    var state by mutableStateOf(PullRequestState())
        private set

    fun onEvent(event: PullRequestEvent) {
        when (event) {
            is PullRequestEvent.OnLoadPullRequest -> loadPullRequests(
                event.user,
                event.repositoryName,
                event.context
            )

            is PullRequestEvent.OnReloadPullRequest -> TODO()

            is PullRequestEvent.OnNavigateUp -> event.navController.navigateUp()
        }
    }

    private fun loadPullRequests(user: String, repositoryName: String, context: Context) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            useCases
                .getPullRequests(user, repositoryName, context)
                .onSuccess { prs ->
                    state = state.copy(
                        pullRequests = prs.toMutableList(),
                        isLoading = false,
                        isError = false
                    )
                }
                .onFailure {
                    state = state.copy(isLoading = false, isError = true)
                }
        }
    }
}