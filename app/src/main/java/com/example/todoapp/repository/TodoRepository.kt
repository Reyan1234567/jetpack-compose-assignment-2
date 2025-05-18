package com.example.todoapp.repository

import com.example.todoapp.data.local.TodoDao
import com.example.todoapp.data.local.TodoEntity
import com.example.todoapp.data.remote.TodoApiService

class TodoRepository(
    private val apiService: TodoApiService,
    private val todoDao: TodoDao
) {
    suspend fun fetchTodos(): List<TodoEntity> {
        return try {
            val todosFromApi = apiService.getTodos()
            val entities = todosFromApi.map {
                TodoEntity(it.id, it.userId, it.title, it.completed)
            }
            todoDao.insertTodos(entities)
            entities
        } catch (e: Exception) {
            todoDao.getAllTodos()
        }
    }

    suspend fun getTodoById(id: Int): TodoEntity? {
        return todoDao.getAllTodos().find { it.id == id }
    }
}
