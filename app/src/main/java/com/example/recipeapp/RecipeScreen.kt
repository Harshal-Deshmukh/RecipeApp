package com.example.recipeapp


import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.grid.items
import androidx.navigation.NavController

@Composable
fun RecipeScreen(modifier: Modifier = Modifier,navController: NavController){
    val RecipeViewModel: MainViewModel = viewModel()
    val viewState by RecipeViewModel.categoriesState
    Box (modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null->{
                Text(text = "ERROR OCCURED")
            }
            else->{
                //Display Categories
                CategoryScreen(categories = viewState.list, navController = navController)

            }
        }
    }

}


@Composable
fun CategoryScreen(categories: List<Category>, navController: NavController) {
    LazyVerticalGrid(GridCells.Fixed(count = 2), modifier = Modifier.fillMaxSize()) {
        items(categories.filter { it.strCategory != "Beef" }) { category ->
            CategoryItem(category = category) {
                navController.navigate("RecipeDetailScreen/${category.strCategory}")
            }
        }
    }
}

//How each Items would look like
@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Gray,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}


