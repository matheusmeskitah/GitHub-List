package com.meskitah.githublist.data.di

import com.meskitah.githublist.data.remote.GitHubListApi
import com.meskitah.githublist.data.remote.GitHubListApi.Companion.BASE_URL
import com.meskitah.githublist.data.repository.GitHubListRepositoryImpl
import com.meskitah.githublist.domain.repository.GitHubListRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object GitHubListDataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(
        client: OkHttpClient,
        moshi: Moshi
    ): GitHubListApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(api: GitHubListApi): GitHubListRepository {
        return GitHubListRepositoryImpl(api = api)
    }
}