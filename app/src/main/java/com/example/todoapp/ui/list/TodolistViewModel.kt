package com.example.todoapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.TodoEntity
import com.example.todoapp.repository.TodoRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.*

class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {
    var todos by mutableStateOf<List<TodoEntity>>(emptyList())
    var isLoading by mutableStateOf(true)
    var errorMessage by mutableStateOf("")

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            try {
                todos = repository.fetchTodos()
            } catch (e: Exception) {
                errorMessage = "Failed to load data."
            } finally {
                isLoading = false
            }
        }
    }
}
