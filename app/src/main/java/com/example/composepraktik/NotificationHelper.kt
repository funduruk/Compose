package com.example.composepraktik

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class NotificationHelper(val context: Context) {

    val manager = context.getSystemService(NotificationManager::class.java)

    companion object {
        const val CHANNEL_ACTIONS = "channel_actions"
        const val KEY_TEXT_REPLY = "key_text_reply"
    }

    fun createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ACTIONS,
                "Уведомления с действиями",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)
        }
    }

    fun simpleNotification(id: Int, title: String, text: String) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ACTIONS)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        manager.notify(id, builder.build())
    }

    fun buttonNotification(id: Int) {
        val intent = Intent(context, ActionService::class.java).apply {
            putExtra("action", "button_click")
        }
        val pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, CHANNEL_ACTIONS)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Уведомление с кнопкой")
            .setContentText("Нажми кнопку")
            .addAction(R.drawable.ic_launcher_foreground, "Нажми", pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        manager.notify(id, builder.build())
    }

    fun replyNotification(id: Int) {
        val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY)
            .setLabel("Введите ответ")
            .build()

        val intent = Intent(context, ActionService::class.java)
        val pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val action = NotificationCompat.Action.Builder(
            R.drawable.ic_launcher_foreground,
            "Ответить",
            pendingIntent
        ).addRemoteInput(remoteInput)
            .build()

        val builder = NotificationCompat.Builder(context, CHANNEL_ACTIONS)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Уведомление с ответом")
            .setContentText("Введите текст")
            .addAction(action)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        manager.notify(id, builder.build())
    }

    fun vibrationNotification(id: Int) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ACTIONS)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Уникальная вибрация")
            .setContentText("Это уведомление вибрирует особым образом")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        builder.setVibrate(longArrayOf(0, 300, 100, 500, 200, 700))

        manager.notify(id, builder.build())
    }

    fun lockScreenNotification(id: Int) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, CHANNEL_ACTIONS)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Открытие с блокировки")
            .setContentText("Нажми, чтобы открыть приложение")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        manager.notify(id, builder.build())
    }
}
