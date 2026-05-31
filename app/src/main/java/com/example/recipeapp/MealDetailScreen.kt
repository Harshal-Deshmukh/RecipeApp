package com.example.recipeapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun MealDetailScreen(mealId: String) {
    val viewModel: MainViewModel = viewModel()
    val mealDetailState by viewModel.mealDetailState

    LaunchedEffect(mealId) {
        viewModel.fetchMealDetail(mealId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            mealDetailState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            mealDetailState.error != null -> {
                Text(text = "ERROR OCCURRED")
            }
            else -> {
                mealDetailState.meal?.let { meal ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        Text(
                            text = meal.strMeal,
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Image(
                            painter = rememberAsyncImagePainter(meal.strMealThumb),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
                        )
                        Text(
                            text = "Ingredients",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        listOfNotNull(
                            meal.strIngredient1, meal.strIngredient2, meal.strIngredient3,
                            meal.strIngredient4, meal.strIngredient5, meal.strIngredient6,
                            meal.strIngredient7, meal.strIngredient8, meal.strIngredient9,
                            meal.strIngredient10
                        ).filter { it.isNotBlank() }.forEach { ingredient ->
                            Text(text = "• $ingredient", modifier = Modifier.padding(vertical = 2.dp))
                        }
                        Text(
                            text = "Instructions",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Text(text = meal.strInstructions)
                    }
                }
            }
        }
    }
}