package com.learner.foodapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun FoodApp(navHostController: NavHostController){

    val viewModel:MainViewModel = viewModel()
    val viewState by viewModel.categoriesState

    NavHost(navController = navHostController, startDestination = "recipescreen" ){
        composable(route = "recipescreen"){
            RecipeScreen(modifier = Modifier.fillMaxSize(),
                viewState = viewState,
                navigateToDetail = {
                navHostController.currentBackStackEntry?.savedStateHandle?.
                    set<Category>("category",it)
                navHostController.navigate("detailscreen")
            })
        }
        composable(route = "detailscreen"){
            val category =navHostController.previousBackStackEntry?.savedStateHandle?.
                    get<Category>("category") ?: Category("0","", "", "")
            CategoryDetailScreen(category = category)
        }
    }
}