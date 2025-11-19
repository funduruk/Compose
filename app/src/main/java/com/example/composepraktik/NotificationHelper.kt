package com.example.composepraktik

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(val context: Context) {

    val manager = context.getSystemService(NotificationManager::class.java)


    companion object {
        const val CHANNEL_SIMPLE = "channel_simple"
        const val CHANNEL_APP = "channel_app"
        const val CHANNEL_SERVICE = "channel_service"
    }

    fun createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel1 = NotificationChannel(
                CHANNEL_SIMPLE,
                "Простые уведомления",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Обычные уведомления с текстом"
                setShowBadge(true)
            }

            val channel2 = NotificationChannel(
                CHANNEL_APP,
                "Открытие приложения",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableVibration(true)
            }

            val channel3 = NotificationChannel(
                CHANNEL_SERVICE,
                "Команды сервису",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            }

            manager.createNotificationChannel(channel1)
            manager.createNotificationChannel(channel2)
            manager.createNotificationChannel(channel3)
        }
    }

    fun builder(channelId: String): NotificationCompat.Builder =
        NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
}
