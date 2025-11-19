package com.example.composepraktik

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composepraktik.models.UserModelView
import com.example.composepraktik.repository.UserRepository

@Composable
fun UserScreen(userModelView: UserModelView) {
    val users by userModelView.users.collectAsState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Добавить пользователя", style = MaterialTheme.typography.titleLarge)
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(value = name, onValueChange = { name = it }, label = { Text("username") })
            Spacer(modifier = Modifier.width(8.dp))
            TextField(value = email, onValueChange = { email = it }, label = { Text("email") })
            Spacer(modifier = Modifier.width(8.dp))
            TextField(value = password, onValueChange = { password = it }, label = { Text("password") })
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (name.isNotBlank() && email.isNotBlank()) {
                    userModelView.addUser(name, email, password)
                    name = ""
                    email = ""
                    password = ""
                }
            }) {
                Text("add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Users:", style = MaterialTheme.typography.titleMedium)
        users.forEach { user ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("${user.username}, ${user.email}, ${user.password}", modifier = Modifier.weight(1f))
                Button(onClick = {

                        userModelView.updateUser(user)

                }) {
                    Text("edit")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(onClick = { userModelView.deleteUser(user) }) {
                    Text("delete")
                }
            }
        }
    }
}
