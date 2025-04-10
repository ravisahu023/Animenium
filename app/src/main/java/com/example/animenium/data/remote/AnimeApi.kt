package com.example.animenium.data.remote

import com.example.animenium.data.model.AnimeDetailsDto
import com.example.animenium.data.model.AnimeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApi {

    @GET("top/anime")
    suspend fun getTopAnime(): Response<AnimeDto>

    @GET("anime/{id}")
    suspend fun getAnimeDetails(
        @Path("id") animeId: Int
    ): Response<AnimeDetailsDto>
}