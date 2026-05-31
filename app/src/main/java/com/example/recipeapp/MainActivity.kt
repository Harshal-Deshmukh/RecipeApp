package com.example.recipeapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.ui.theme.RecipeAppTheme
import com.example.recipeapp.ui.theme.RecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "RecipeScreen") {
                        composable("RecipeScreen") {
                            RecipeScreen(navController = navController)
                        }
                        composable("RecipeDetailScreen/{categoryName}") { backStackEntry ->
                            val categoryName =
                                backStackEntry.arguments?.getString("categoryName") ?: ""
                            RecipeDetailScreen(
                                categoryName = categoryName,
                                navController = navController
                            )
                        }
                        composable("MealDetailScreen/{mealId}") { backStackEntry ->
                            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
                            MealDetailScreen(mealId = mealId)
                        }

                    }
                }
            }
        }
    }
}
