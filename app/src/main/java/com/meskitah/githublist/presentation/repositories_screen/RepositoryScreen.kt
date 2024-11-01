package com.meskitah.githublist.presentation.repositories_screen

import androidx.compose.foundation.Image
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
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.meskitah.githublist.R
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.presentation.components.RepositoryItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryScreen(
    repositories: LazyPagingItems<Repository>,
    onLoadRepositories: () -> Unit,
    onCardClick: (Repository) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val context = LocalContext.current
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(repositories.loadState.append) {
        if (repositories.loadState.append is LoadState.Error) {
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

        //region First load
        when (repositories.loadState.refresh) {
            is LoadState.Error -> {
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
                        onClick = onLoadRepositories
                    ) {
                        Text(
                            text = stringResource(R.string.retry),
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
            }

            is LoadState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(48.dp))
                }
            }

            is LoadState.NotLoading -> isRefreshing = false
        }
        //endregion

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                repositories.refresh()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = repositories.itemCount
                ) { index ->
                    repositories[index]?.let {
                        RepositoryItem(
                            repository = it,
                            onCardClick = { onCardClick(it) }
                        )
                    }
                }

                //region Pagination
                when (repositories.loadState.append) {
                    is LoadState.Loading -> item { CircularProgressIndicator() }
                    is LoadState.Error -> Unit
                    is LoadState.NotLoading -> Unit
                }
                //endregion
            }
        }
    }
}