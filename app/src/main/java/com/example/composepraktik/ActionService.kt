package com.example.composepraktik

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ActionService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.getStringExtra("action")
        val reply = intent?.getStringExtra("reply")
        Log.d("ActionService", "Action: $action, Reply: $reply")
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
