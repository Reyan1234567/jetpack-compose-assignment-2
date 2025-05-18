package com.example.todoapp.data.local


import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    suspend fun getAllTodos(): List<TodoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: List<TodoEntity>)
}
