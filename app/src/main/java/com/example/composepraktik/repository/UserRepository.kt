package com.example.composepraktik.repository

import androidx.lifecycle.LiveData
import com.example.composepraktik.dao.UserDao
import com.example.composepraktik.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val userList: List<User> = userDao.getUsers()

    fun addUser(user: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.addUser(user) 
        }
    }

    fun updateUser(user: User){
        coroutineScope.launch {
            userDao.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.deleteUser(user)
        }
    }
}