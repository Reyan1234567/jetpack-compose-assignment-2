package com.example.todoapp.ui.detail


import androidx.lifecycle.ViewModel
import com.example.todoapp.data.local.TodoEntity
import com.example.todoapp.repository.TodoRepository
import kotlinx.coroutines.runBlocking

class TodoDetailViewModel(private val repository: TodoRepository) : ViewModel() {
    fun getTodoById(id: Int): TodoEntity? = runBlocking {
        repository.getTodoById(id)
    }
}
