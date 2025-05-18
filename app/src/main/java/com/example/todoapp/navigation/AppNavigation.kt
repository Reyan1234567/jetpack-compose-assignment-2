package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.todoapp.ui.list.TodoListScreen
import com.example.todoapp.ui.detail.TodoDetailScreen
import com.example.todoapp.ui.detail.TodoDetailViewModel
import com.example.todoapp.ui.list.TodoListViewModel

@Composable
fun AppNavigation(
    listViewModel: TodoListViewModel,
    detailViewModelFactory: (Int) -> TodoDetailViewModel
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        composable("list") {
            TodoListScreen(viewModel = listViewModel) { id ->
                navController.navigate("detail/$id")
            }
        }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
            TodoDetailScreen(
                todoId = id,
                viewModel = detailViewModelFactory(id),
                navController = navController
            )
        }
    }
}
