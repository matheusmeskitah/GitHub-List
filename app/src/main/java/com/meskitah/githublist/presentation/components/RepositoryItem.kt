package com.meskitah.githublist.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.ForkRight
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.meskitah.githublist.R
import com.meskitah.githublist.core.ui.DarkerYellow
import com.meskitah.githublist.core.ui.Yellow
import com.meskitah.githublist.domain.model.Owner
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.presentation.repositories_screen.RepositoriesEvent
import com.meskitah.githublist.ui.theme.GitHubListTheme

@Composable
fun RepositoryItem(
    repository: Repository,
    navController: NavController
) {
    Card(onClick = { RepositoriesEvent.OnRepositoryClick(repository, navController) }) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(repository.owner.avatarUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(id = R.string.repo_owner_cd),
                    contentScale = ContentScale.Crop,
                    placeholder = rememberVectorPainter(Icons.Default.AccountCircle),
                    modifier = Modifier.clip(CircleShape)
                )

                Text(text = repository.owner.login, style = MaterialTheme.typography.labelMedium)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(text = repository.name, style = MaterialTheme.typography.titleMedium)
                Text(text = repository.description, style = MaterialTheme.typography.bodySmall)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.ForkRight,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (isSystemInDarkTheme()) DarkerYellow else Yellow
                    )
                    Text(
                        text = repository.forks.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isSystemInDarkTheme()) DarkerYellow else Yellow
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (isSystemInDarkTheme()) DarkerYellow else Yellow
                    )
                    Text(
                        text = repository.stargazersCount.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isSystemInDarkTheme()) DarkerYellow else Yellow
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RepositoryItemPreview() {
    GitHubListTheme {
        RepositoryItem(
            repository = Repository(
                id = 1,
                description = "This is a very cool description",
                11,
                "My repository",
                owner = Owner(id = 11, login = "my_owner", avatarUrl = ""),
                stargazersCount = 22
            ),
            navController = rememberNavController()
        )
    }
}