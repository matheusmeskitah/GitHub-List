package com.meskitah.githublist.data.mapper

import android.content.Context
import com.meskitah.githublist.core.util.formatToDate
import com.meskitah.githublist.data.remote.dto.PullRequestDTO
import com.meskitah.githublist.data.remote.dto.UserPRDTO
import com.meskitah.githublist.domain.model.PullRequest
import com.meskitah.githublist.domain.model.UserPR

fun PullRequestDTO.toPullRequest(context: Context): PullRequest? {
    return id?.let { id ->
        user?.toUserPr()?.let { user ->
            PullRequest(
                id = id,
                body = body ?: "",
                createdAt = created_at?.formatToDate(context) ?: "",
                title = title ?: "",
                user = user
            )
        }
    }
}

fun UserPRDTO.toUserPr(): UserPR? {
    return id?.let {
        UserPR(
            id = it,
            login = login ?: "",
            avatarUrl = avatar_url ?: ""
        )
    }
}