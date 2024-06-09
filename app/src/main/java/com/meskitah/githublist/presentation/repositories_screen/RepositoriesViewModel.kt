package com.meskitah.githublist.presentation.repositories_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.domain.use_case.GitHubUseCases
import com.meskitah.githublist.presentation.navigation.ScreenPullRequests
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val useCases: GitHubUseCases
) : ViewModel() {

    private val _state: MutableStateFlow<PagingData<Repository>> =
        MutableStateFlow(value = PagingData.empty())
    val state: MutableStateFlow<PagingData<Repository>> get() = _state

    init {
        onEvent(RepositoriesEvent.OnLoadRepositories)
    }

    fun onEvent(event: RepositoriesEvent) {
        when (event) {
            is RepositoriesEvent.OnLoadRepositories -> loadRepositories()

            is RepositoriesEvent.OnRepositoryClick -> {
                event.navController.navigate(
                    ScreenPullRequests(
                        creator = event.repository.owner.login,
                        repositoryName = event.repository.name
                    )
                )
            }
        }
    }

    private fun loadRepositories() {
        viewModelScope.launch {
            useCases
                .getRepositories()
                .distinctUntilChanged()
                .filterNotNull()
                .cachedIn(viewModelScope)
                .collect { _state.value = it }
        }
    }
}