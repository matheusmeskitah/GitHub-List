package com.meskitah.githublist.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.meskitah.githublist.R
import com.meskitah.githublist.domain.model.PullRequest
import com.meskitah.githublist.domain.model.UserPR
import com.meskitah.githublist.ui.theme.GitHubListTheme

@Composable
fun PullRequestItem(pullRequest: PullRequest) {
    Card(onClick = {}) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = pullRequest.title, style = MaterialTheme.typography.titleMedium)
            Text(text = pullRequest.body, style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pullRequest.user.avatarUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(id = R.string.repo_owner_cd),
                    contentScale = ContentScale.Crop,
                    placeholder = rememberVectorPainter(Icons.Default.AccountCircle),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = pullRequest.user.login, style = MaterialTheme.typography.labelMedium)

                Spacer(modifier = Modifier.weight(1f))

                Text(text = pullRequest.createdAt, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Preview
@Composable
fun PullRequestItemPreview() {
    GitHubListTheme {
        PullRequestItem(
            pullRequest = PullRequest(
                id = 1,
                body = "This is a very cool description",
                createdAt = "11/10/2024",
                title = "My repository",
                user = UserPR(id = 11, login = "my_owner", avatarUrl = "")
            )
        )
    }
}