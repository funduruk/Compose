package com.example.composepraktik

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val command = intent?.getStringExtra("command") ?: ""
        Log.d("SERVICE", "Получена команда: $command")
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
