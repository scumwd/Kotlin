package com.example.firsapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReciver : BroadcastReceiver() {

    override fun onReceive(context: Context , intent: Intent) {
        val service = NotificationService(context)
        service.showNotification(context,"Alarm")
    }
}
