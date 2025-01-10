package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun RecipieApp(navController: NavHostController){
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState
    NavHost(navController = navController,
        startDestination = Screen.RecipeScreen.route){

        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(viewstate = viewstate , navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }

        composable(route = Screen.DetailScreen.route){
            val category = navController
                .previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?:
                Category("","","","")
            CategoryDetailScreen(category = category)

        }
    }
}