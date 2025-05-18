
package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.todoapp.data.local.AppDatabase
import com.example.todoapp.data.remote.TodoApiService
import com.example.todoapp.navigation.AppNavigation
import com.example.todoapp.repository.TodoRepository
import com.example.todoapp.ui.detail.TodoDetailViewModel
import com.example.todoapp.ui.list.TodoListViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todo-db"
        ).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(TodoApiService::class.java)
        val repository = TodoRepository(apiService, db.todoDao())

        val listViewModel = TodoListViewModel(repository)

        setContent {
            AppNavigation(
                listViewModel = listViewModel,
                detailViewModelFactory = { id -> TodoDetailViewModel(repository) }
            )
        }
    }
}
