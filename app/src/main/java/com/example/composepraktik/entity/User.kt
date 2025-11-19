package com.example.composepraktik.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users")
class User {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var email: String? = null
    var username: String? = null
    var password: String? = null

    constructor() {}

    constructor(id: Int, username: String, email: String, password: String) {
        this.id = id
        this.username = username
        this.email = email
        this.password = password
    }
    constructor(username: String, email: String, password: String) {
        this.username = username
        this.email = email
        this.password = password
    }
}