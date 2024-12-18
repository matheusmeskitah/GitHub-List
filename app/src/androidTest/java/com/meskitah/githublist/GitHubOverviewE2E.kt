package com.meskitah.githublist

import android.content.Context
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.meskitah.githublist.domain.use_case.GetPullRequestsUseCase
import com.meskitah.githublist.domain.use_case.GetRepositoriesUseCase
import com.meskitah.githublist.domain.use_case.GitHubUseCases
import com.meskitah.githublist.presentation.navigation.NavigationEvents
import com.meskitah.githublist.presentation.navigation.ScreenPullRequests
import com.meskitah.githublist.presentation.navigation.ScreenRepositoryList
import com.meskitah.githublist.presentation.pull_requests_screen.PullRequestEvent
import com.meskitah.githublist.presentation.pull_requests_screen.PullRequestViewModel
import com.meskitah.githublist.presentation.pull_requests_screen.PullRequestsScreen
import com.meskitah.githublist.presentation.repositories_screen.RepositoriesEvent
import com.meskitah.githublist.presentation.repositories_screen.RepositoriesViewModel
import com.meskitah.githublist.presentation.repositories_screen.RepositoryScreen
import com.meskitah.githublist.repository.GitHubRepositoryFake
import com.meskitah.githublist.ui.theme.GitHubListTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockkStatic
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class GitHubOverviewE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repository: GitHubRepositoryFake
    private lateinit var useCases: GitHubUseCases
    private lateinit var repositoriesViewModel: RepositoriesViewModel
    private lateinit var pullRequestViewModel: PullRequestViewModel

    private lateinit var navController: NavHostController

    private lateinit var context: Context

    @Before
    fun setUp() {
        mockkStatic(Log::class)

        repository = GitHubRepositoryFake()
        useCases = GitHubUseCases(
            getRepositoriesUseCase = GetRepositoriesUseCase(repository),
            getPullRequestsUseCase = GetPullRequestsUseCase(repository)
        )
        repositoriesViewModel = RepositoriesViewModel(useCases)
        pullRequestViewModel = PullRequestViewModel(useCases)

        context = InstrumentationRegistry.getInstrumentation().targetContext

        composeRule.activity.setContent {
            GitHubListTheme {
                navController = rememberNavController()
                val snackbarState = remember { SnackbarHostState() }

                NavHost(
                    navController = navController,
                    startDestination = ScreenRepositoryList
                ) {
                    composable<ScreenRepositoryList> {
                        RepositoryScreen(
                            repositories = repositoriesViewModel.state.collectAsLazyPagingItems(),
                            onLoadRepositories = {
                                repositoriesViewModel.onEvent(RepositoriesEvent.OnLoadRepositories)
                            },
                            onCardClick = {
                                NavigationEvents.onEvent(
                                    navController,
                                    NavigationEvents.OnNavigateToPullRequests(it)
                                )
                            },
                            snackbarHostState = snackbarState,
                        )
                    }

                    composable<ScreenPullRequests> {
                        val args = it.toRoute<ScreenPullRequests>()
                        PullRequestsScreen(
                            state = pullRequestViewModel.state,
                            snackbarHostState = snackbarState,
                            repositoryName = args.repositoryName,
                            setScreenLoaded = pullRequestViewModel::setScreenLoaded,
                            onNavigateUp = {
                                NavigationEvents.onEvent(
                                    navController,
                                    NavigationEvents.OnNavigateUp
                                )
                            },
                            onLoadPullRequest = {
                                pullRequestViewModel.onEvent(
                                    PullRequestEvent.OnLoadPullRequest(
                                        args.creator,
                                        args.repositoryName
                                    )
                                )
                            },
                            onReloadPullRequest = {
                                pullRequestViewModel.onEvent(
                                    PullRequestEvent.OnReloadPullRequest(
                                        args.creator,
                                        args.repositoryName
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    @Test
    fun loadRepositories_openRepoPRs() {
        composeRule
            .onAllNodesWithContentDescription(context.getString(R.string.repo_owner_cd))
            .onFirst()
            .assertIsDisplayed()

        composeRule
            .onAllNodesWithContentDescription(context.getString(R.string.repo_owner_cd))
            .onFirst()
            .performClick()

        assertThat(navController.currentDestination?.route)
            .contains(ScreenPullRequests::class.java.simpleName)

        composeRule
            .onAllNodesWithContentDescription(context.getString(R.string.repo_owner_cd))
            .onFirst()
            .assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription(context.getString(R.string.back_icon))
            .performClick()

        assertThat(navController.currentDestination?.route)
            .contains(ScreenRepositoryList::class.java.simpleName)
    }
}