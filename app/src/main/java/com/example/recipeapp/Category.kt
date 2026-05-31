package com.example.recipeapp

data class Category(val idCategory: Int,
                    val strCategory: String,
                    val strCategoryThumb: String,
                    val strCategoryDescription: String)

data class CategoryResponse(val categories: List<Category>)

data class MealResponse(val meals: List<Meal>)

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)
data class MealDetail(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstructions: String,
    val strIngredient1: String?, val strIngredient2: String?, val strIngredient3: String?,
    val strIngredient4: String?, val strIngredient5: String?, val strIngredient6: String?,
    val strIngredient7: String?, val strIngredient8: String?, val strIngredient9: String?,
    val strIngredient10: String?
)

data class MealDetailResponse(val meals: List<MealDetail>)