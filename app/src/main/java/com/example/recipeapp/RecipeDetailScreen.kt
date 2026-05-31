package com.example.recipeapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeDetailScreen(categoryName: String, navController: NavController) {
    val viewModel: MainViewModel = viewModel()
    val mealsState by viewModel.mealsState

    LaunchedEffect(categoryName) {
        viewModel.fetchMealsByCategory(categoryName)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            mealsState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            mealsState.error != null -> {
                Text(text = "ERROR OCCURRED")
            }
            else -> {
                LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                    items(mealsState.list) { meal ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .clickable {
                                    navController.navigate("MealDetailScreen/${meal.idMeal}")
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(meal.strMealThumb),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize().aspectRatio(1f)
                            )
                            Text(
                                text = meal.strMeal,
                                color = Color.Gray,
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}