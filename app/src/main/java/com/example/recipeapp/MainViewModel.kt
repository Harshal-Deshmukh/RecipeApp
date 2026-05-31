package com.example.recipeapp


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {

    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    init {
        viewModelScope.launch {
            fetchCategories()
        }
    }

    private suspend fun fetchCategories(){
        try {
            val response = RecipeService.getCategories()
            _categoriesState.value = _categoriesState.value.copy(
                list = response.categories,
                loading = false,
                error = null
            )
        } catch (e: Exception){
            _categoriesState.value = _categoriesState.value.copy(
                loading = false,
                error = "Error fetching Categories ${e.message}"
            )
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )

    private val _mealsState = mutableStateOf(MealState())
    val mealsState: State<MealState> = _mealsState

    fun fetchMealsByCategory(category: String) {
        viewModelScope.launch {
            try {
                val response = RecipeService.getMealsByCategory(category)
                _mealsState.value = _mealsState.value.copy(
                    list = response.meals,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _mealsState.value = _mealsState.value.copy(
                    loading = false,
                    error = "Error fetching Meals ${e.message}"
                )
            }
        }
    }

    data class MealState(
        val loading: Boolean = true,
        val list: List<Meal> = emptyList(),
        val error: String? = null
    )

    private val _mealDetailState = mutableStateOf(MealDetailState())
    val mealDetailState: State<MealDetailState> = _mealDetailState

    fun fetchMealDetail(mealId: String) {
        viewModelScope.launch {
            try {
                val response = RecipeService.getMealDetail(mealId)
                _mealDetailState.value = _mealDetailState.value.copy(
                    meal = response.meals.firstOrNull(),
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _mealDetailState.value = _mealDetailState.value.copy(
                    loading = false,
                    error = "Error fetching detail ${e.message}"
                )
            }
        }
    }

    data class MealDetailState(
        val loading: Boolean = true,
        val meal: MealDetail? = null,
        val error: String? = null
    )
}
