package com.meskitah.githublist.data.repository

import android.util.Log
import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.meskitah.githublist.data.remote.GitHubApi
import com.meskitah.githublist.data.remote.dto.GitHubDTO
import com.meskitah.githublist.data.remote.dto.OwnerDTO
import com.meskitah.githublist.data.remote.dto.RepositoryDTO
import com.meskitah.githublist.data.remote.invalidPullRequestListResponse
import com.meskitah.githublist.data.remote.validPullRequestListResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.mockkStatic
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GitHubRepositoryImplTest {

    private lateinit var repository: GitHubRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var moshi: Moshi
    private lateinit var api: GitHubApi
    private var mockitoApi: GitHubApi = mock()
    private lateinit var pagingSource: RepositoriesPagingSource

    companion object {
        val gitResponse = GitHubDTO(
            items = listOf(
                RepositoryDTO(
                    id = 1,
                    name = "repo1",
                    description = "desc1",
                    owner = OwnerDTO(id = 11, login = "login1", avatar_url = null),
                    forks = 1,
                    stargazers_count = 1
                )
            ),
            total_count = 10,
            incomplete_results = false
        )
    }

    @Before
    fun setUp() {
        mockkStatic(Log::class)

        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(GitHubApi::class.java)
        repository = GitHubRepositoryImpl(api)

        pagingSource = RepositoriesPagingSource(mockitoApi)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Search repos, null response, return error`() = runBlocking {
        given(mockitoApi.getRepos(any(), any(), any())).willReturn(null)

        assertThat(
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
            .isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }

    @Test
    fun `Search repos, first response, return correct`() = runBlocking {
        given(mockitoApi.getRepos(any(), any(), any())).willReturn(gitResponse)

        assertThat(
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
            .isInstanceOf(PagingSource.LoadResult.Page::class.java)
    }

    @Test
    fun `Search pull requests, valid response, return correct`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validPullRequestListResponse)
        )
        val result = repository.getPullRequests("user", "repo")

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `Search pull requests, invalid response code, return failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validPullRequestListResponse)
        )
        val result = repository.getPullRequests("user", "repo")

        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `Search pull requests, malformed response, return correct`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(invalidPullRequestListResponse)
        )
        val result = repository.getPullRequests("user", "repo")

        assertThat(result.isSuccess).isTrue()
    }
}