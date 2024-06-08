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
        onEvent(RepositoriesEvent.OnLoadSports)
    }

    fun onEvent(event: RepositoriesEvent) {
        when (event) {
            is RepositoriesEvent.OnLoadSports -> loadSports()

            is RepositoriesEvent.OnReloadSports -> reloadSports()

            is RepositoriesEvent.OnSportsClick -> {
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
                .cachedIn(viewModelScope)
                .collect { _state.value = it }
        }
    }

    private fun reloadSports() {
//        viewModelScope.launch {
//            useCases
//                .getRepositories(state.repositories)
//                .onSuccess { sports ->
//                    state = state.copy(repositories = sports.toMutableList())
//                    _uiEvent.send(UiEvent.Success)
//                }
//                .onFailure {
//                    state = state.copy(isError = true)
//                    _uiEvent.send(
//                        UiEvent.ShowSnackbar(UiText.DynamicString(it.localizedMessage ?: ""))
//                    )
//                }
//        }
    }
}