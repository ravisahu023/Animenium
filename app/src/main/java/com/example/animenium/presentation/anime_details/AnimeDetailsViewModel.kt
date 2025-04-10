package com.example.animenium.presentation.anime_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animenium.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    private val repository: AnimeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(AnimeDetailState())
        private set

    init {
        val animeId = savedStateHandle.get<String>("id")?.toIntOrNull()
        animeId?.let {
            fetchAnimeDetail(it)
        }
    }

    private fun fetchAnimeDetail(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val anime = repository.getAnimeDetails(id)
                state = state.copy(anime = anime.data, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
            }
        }
    }
}
