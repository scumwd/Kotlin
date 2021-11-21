package com.example.firsapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.timepicker.MaterialTimePicker
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    companion object{
        private const val ALARM_REQUEST_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val setAlarm = findViewById<Button>(R.id.btn_start)

        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val hour = findViewById<EditText>(R.id.et_hour)
        val min = findViewById<EditText>(R.id.et_min)
        val intent = Intent(this, AlarmReciver::class.java)
        val pendingIntent= PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        setAlarm.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR,hour.text.toString().toInt())
            calendar.set(Calendar.MINUTE, min.text.toString().toInt())
            val result: Long = calendar.timeInMillis
            alarmManager.set(AlarmManager.RTC_WAKEUP, result, pendingIntent)
        }
    }
}


