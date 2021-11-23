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
        val stopAlarm = findViewById<Button>(R.id.btn_stop)

        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val hour = findViewById<EditText>(R.id.et_hour)
        val min = findViewById<EditText>(R.id.et_min)
        val intent = Intent(this, AlarmReciver::class.java)

        setAlarm.setOnClickListener {
            val hour = if (!hour.text.toString().isEmpty())  hour.text.toString() else "0"
            val min = if (!min.text.toString().isEmpty())min.text.toString() else "0"

            val pendingIntent= PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE,intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR,hour.toInt())
            calendar.set(Calendar.MINUTE,min.toInt())
            val result: Long = calendar.timeInMillis
            alarmManager.set(AlarmManager.RTC_WAKEUP, result, pendingIntent)
        }
        stopAlarm.setOnClickListener {
                val pendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, Intent(this, AlarmReciver::class.java), PendingIntent.FLAG_NO_CREATE)
                pendingIntent?.cancel()
        }

    }
}


