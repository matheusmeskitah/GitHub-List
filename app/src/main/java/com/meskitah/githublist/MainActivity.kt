package com.meskitah.githublist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.meskitah.githublist.presentation.navigation.GitHubListRoute
import com.meskitah.githublist.presentation.navigation.addGitHubListGraph
import com.meskitah.githublist.ui.theme.GitHubListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitHubListTheme {
                val navController = rememberNavController()
                val snackbarState = remember { SnackbarHostState() }

                NavHost(
                    navController = navController,
                    startDestination = GitHubListRoute.RepositoryListRoute.route
                ) {
                    addGitHubListGraph(navController, snackbarState)
                }
            }
        }
    }
}