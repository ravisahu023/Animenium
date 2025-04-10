package com.example.animenium.presentation.anime_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animenium.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    var state by mutableStateOf(AnimeListState())
        private set

    init {
        fetchTopAnime()
    }

    private fun fetchTopAnime() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val result = repository.getTopAnime()
                state = state.copy(animeList = result.data, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
            }
        }
    }
}
