package com.meskitah.githublist.presentation.repositories_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.meskitah.githublist.R
import com.meskitah.githublist.core.util.UiEvent
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.presentation.components.RepositoryItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryScreen(
    viewModel: RepositoriesViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val context = LocalContext.current
    var isRefreshing by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> {
                    isRefreshing = false
                }

                is UiEvent.ShowSnackbar -> {
                    isRefreshing = false
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium
                )
            })
        }
    ) { padding ->
        val repositories: LazyPagingItems<Repository> = viewModel.state.collectAsLazyPagingItems()
        val coroutineScope = rememberCoroutineScope()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = repositories.itemCount
            ) { index ->
                repositories[index]?.let { RepositoryItem(repository = it) }
            }

            //region First load
            when (repositories.loadState.refresh) {
                is LoadState.Error -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(36.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.error_image),
                                contentDescription = stringResource(R.string.error_image),
                                modifier = Modifier.size(200.dp)
                            )

                            Text(
                                text = stringResource(R.string.oops),
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Text(
                                text = stringResource(R.string.error_something_went_wrong),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            Button(
                                onClick = { viewModel.onEvent(RepositoriesEvent.OnLoadRepositories) }
                            ) {
                                Text(
                                    text = stringResource(R.string.retry),
                                    style = MaterialTheme.typography.labelLarge,
                                )
                            }
                        }
                    }
                }

                is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray.copy(0.4F))
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(48.dp))
                        }
                    }
                }

                is LoadState.NotLoading -> Unit
            }
            //endregion

            //region Pagination
            when (repositories.loadState.append) {
                is LoadState.Error -> item {
                    coroutineScope.launch {
                        val snackbarResult = snackbarHostState.showSnackbar(
                            message = "Error: " + (repositories.loadState.append as LoadState.Error).error.message,
                            actionLabel = context.resources.getString(R.string.retry)
                        )
                        when (snackbarResult) {
                            SnackbarResult.ActionPerformed -> repositories.retry()
                            else -> Unit
                        }
                    }
                }

                is LoadState.Loading -> item { CircularProgressIndicator() }
                is LoadState.NotLoading -> Unit
            }
            //endregion
        }
    }
}