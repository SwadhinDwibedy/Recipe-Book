package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items

@Composable
fun RecipeScreen(modifier: Modifier= Modifier,
                 viewstate: MainViewModel.RecipeState,
                 navigateToDetail: (Category) -> Unit
                 ){
//    val recipeViewModel: MainViewModel = viewModel()
//    val viewstate by recipeViewModel.categoriesState
    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewstate.error != null ->{
                Text("Error Occurred")
            }
            else ->{
                CategoryScreen(categories = viewstate.list,
                    navigateToDetail
                )
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>,
                   navigaToDetail: (Category) -> Unit
                   ) {
    LazyVerticalGrid(GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(categories) {
                category ->
            CategoryItem(category = category, navigaToDetail)
        }
    }
}

@Composable
fun CategoryItem(category: Category,
                 navigateToDetail: (Category) -> Unit
                 ){
    Column (modifier = Modifier.
                padding(8.dp).
                fillMaxSize()
                    .clickable{navigateToDetail(category)},
            horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )

        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

