package com.meskitah.githublist.presentation.pull_requests_screen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.meskitah.githublist.R
import com.meskitah.githublist.presentation.components.PullRequestItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullRequestsScreen(
    state: PullRequestState,
    snackbarHostState: SnackbarHostState,
    repositoryName: String,
    setScreenLoaded: () -> Unit,
    onNavigateUp: () -> Unit,
    onLoadPullRequest: () -> Unit,
    onRefresh: (Boolean) -> Unit
) {
    LaunchedEffect(state.isFirstLoad) {
        if (state.isFirstLoad)
            onLoadPullRequest()

        setScreenLoaded()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = repositoryName,
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_icon),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { padding ->
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = {
                onRefresh(true)
                onLoadPullRequest()
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
                    count = state.pullRequests.size
                ) { index ->
                    PullRequestItem(pullRequest = state.pullRequests[index])
                }
            }
        }

        //region Error
        if (state.isError) {
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

                Button(onClick = onLoadPullRequest) {
                    Text(
                        text = stringResource(R.string.retry),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
        //endregion

        //region Loading
        if (state.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.size(48.dp))
            }
        }
        //endregion
    }
}