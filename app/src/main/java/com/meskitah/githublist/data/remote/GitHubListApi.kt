package com.meskitah.githublist.data.remote

import com.meskitah.githublist.data.remote.dto.GitHubDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubListApi {

    @GET("repositories")
    suspend fun getRepos(
        @Query("q") language: String = "language:Java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int,
    ): GitHubDTO

    companion object {
        const val BASE_URL = "https://api.github.com/search/"
    }
}