package com.example.composepraktik.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composepraktik.dao.UserDao
import com.example.composepraktik.dataBase.Entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}