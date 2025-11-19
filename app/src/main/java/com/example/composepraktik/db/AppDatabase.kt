package com.example.composepraktik.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composepraktik.dao.UserDao
import com.example.composepraktik.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}