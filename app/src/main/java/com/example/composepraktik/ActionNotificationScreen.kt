package com.example.composepraktik

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionNotificationScreen(notificationHelper: NotificationHelper) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        Button(onClick = { notificationHelper.buttonNotification(1) }) {
            Text("1. Уведомление с кнопкой")
        }

        Button(onClick = { notificationHelper.replyNotification(2) }) {
            Text("2. Уведомление с полем ввода")
        }

        Button(onClick = { notificationHelper.vibrationNotification(3) }) {
            Text("3. Уникальная вибрация")
        }

        Button(onClick = { notificationHelper.lockScreenNotification(4) }) {
            Text("4. Открытие с экрана блокировки")
        }
    }
}
