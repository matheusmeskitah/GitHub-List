package com.meskitah.githublist.data.remote

import com.meskitah.githublist.data.remote.dto.GitHubDTO
import com.meskitah.githublist.data.remote.dto.PullRequestDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") language: String = "language:Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int,
    ): GitHubDTO

    @GET("repos/{user}/{repo}/pull")
    suspend fun getRepo(
        @Path("user") user: String,
        @Path("repo") repo: String
    ): List<PullRequestDTO>

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}