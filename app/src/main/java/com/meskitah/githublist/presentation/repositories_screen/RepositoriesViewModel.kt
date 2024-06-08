package com.meskitah.githublist.presentation.repositories_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.meskitah.githublist.core.util.UiEvent
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.domain.use_case.RepositoriesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val useCases: RepositoriesUseCases
) : ViewModel() {

    private val _state: MutableStateFlow<PagingData<Repository>> =
        MutableStateFlow(value = PagingData.empty())
    val state: MutableStateFlow<PagingData<Repository>> get() = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(RepositoriesEvent.OnLoadRepositories)
    }

    fun onEvent(event: RepositoriesEvent) {
        when (event) {
            is RepositoriesEvent.OnLoadRepositories -> loadSports()

            is RepositoriesEvent.OnRepositoryClick -> {
//                state = state.copy(
//                    repositories = state.repositories.toMutableList()
//                )
            }
        }
    }

    private fun loadSports() {
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