package com.example.composepraktik.models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.composepraktik.db.AppDatabase
import com.example.composepraktik.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserModelView(app: Application) : ViewModel() {
    private val db = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "users.db"
    ).build()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _users.value = db.userDao().getUsers()
        }
    }

    fun addUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            db.userDao().addUser(User(username = name, email = email, password = password))
            loadUsers()
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            db.userDao().updateUser(user)
            loadUsers()
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            db.userDao().deleteUser(user)
            loadUsers()
        }
    }
}