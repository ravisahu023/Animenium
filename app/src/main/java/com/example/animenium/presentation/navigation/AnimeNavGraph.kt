package com.example.animenium.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animenium.presentation.anime_details.AnimeDetailsScreen
import com.example.animenium.presentation.anime_list.AnimeListScreen

@Composable
fun AnimeNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "anime_list") {
        composable("anime_list") {
            AnimeListScreen(onAnimeClick = { animeId ->
                navController.navigate("anime_detail/$animeId")
            })
        }
        composable("anime_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                AnimeDetailsScreen(animeDetailsID = it) {
                    navController.popBackStack()
                }
            }
        }
    }
}
