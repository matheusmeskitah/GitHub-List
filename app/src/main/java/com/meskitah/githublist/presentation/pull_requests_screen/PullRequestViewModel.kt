package com.meskitah.githublist.presentation.pull_requests_screen

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
                event.repositoryName
            )

            is PullRequestEvent.OnReloadPullRequest -> reloadPullRequests(
                event.user,
                event.repositoryName
            )
        }
    }

    fun setScreenLoaded() {
        state = state.copy(isFirstLoad = false)
    }

    private fun loadPullRequests(user: String, repositoryName: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            useCases
                .getPullRequestsUseCase(user, repositoryName)
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

    private fun reloadPullRequests(user: String, repositoryName: String) {
        viewModelScope.launch {
            state = state.copy(isRefreshing = true)

            useCases
                .getPullRequestsUseCase(user, repositoryName)
                .onSuccess { prs ->
                    state = state.copy(
                        pullRequests = prs.toMutableList(),
                        isLoading = false,
                        isError = false,
                        isRefreshing = false
                    )
                }
                .onFailure {
                    state = state.copy(isLoading = false, isError = true)
                }
        }
    }
}