package com.example.animenium.domain.repository

import com.example.animenium.data.model.AnimeDetailsDto
import com.example.animenium.data.model.AnimeDto
import com.example.animenium.data.remote.AnimeApi
import com.example.animenium.data.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val api: AnimeApi
) : AnimeRepository {

    override suspend fun getTopAnime(): AnimeDto {
        val response = api.getTopAnime()
        if (response.isSuccessful) {
            return response.body() ?: AnimeDto(emptyList())
        } else {
            throw Exception("API Error: ${response.code()}")
        }
    }

    override suspend fun getAnimeDetails(id: Int): AnimeDetailsDto {
        val response = api.getAnimeDetails(id)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Failed to fetch details")
        }
    }

}