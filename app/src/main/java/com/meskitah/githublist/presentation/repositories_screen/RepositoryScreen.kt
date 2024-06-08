package com.meskitah.githublist.presentation.repositories_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.meskitah.githublist.domain.model.Repository

@Composable
fun RepositoryScreen(
    viewModel: RepositoriesViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val repositories: LazyPagingItems<Repository> = viewModel.state.collectAsLazyPagingItems()
    Scaffold {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(repositories.itemCount) { index ->
                Text(text = repositories[index]?.name ?: "")
            }
        }
    }
}