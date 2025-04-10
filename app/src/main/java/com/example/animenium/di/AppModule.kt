package com.example.animenium.di

import com.example.animenium.data.remote.AnimeApi
import com.example.animenium.data.repository.AnimeRepository
import com.example.animenium.domain.repository.AnimeRepositoryImpl
import com.example.animenium.util.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAnimeApi(retrofit: Retrofit) : AnimeApi = retrofit.create(AnimeApi::class.java)

    @Provides
    @Singleton
    fun provideAnimeRepository(api: AnimeApi): AnimeRepository =
        AnimeRepositoryImpl(api)


}