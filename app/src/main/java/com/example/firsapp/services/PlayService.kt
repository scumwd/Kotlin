package com.example.firsapp.services

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import com.example.firsapp.Song
import com.example.firsapp.SoundNotification

class PlayService: Service() {
    var activity:Callbacks? = null
    val mBinder: IBinder = LocalBinder()
    lateinit var notificationManager: NotificationManager
    lateinit var curSong: Song
    private lateinit var mp: MediaPlayer


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            curSong = intent.getSerializableExtra("song") as Song
            mp = MediaPlayer.create(this, curSong.audio)
            mp.start()
            mp.setOnCompletionListener {
                applicationContext.sendBroadcast(Intent("TRACKS_TRACK").putExtra("actioname",SoundNotification.ACTION_NEXT))
            }
        }
        return START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                SoundNotification.CHANNEL_ID,
                "MNC",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
        notificationManager.cancelAll()
        mp.stop()
    }

    inner class LocalBinder : Binder() {
        fun getServiceInstance(): PlayService {
            return this@PlayService
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

    fun registerClient(activity: Activity?) {
        this.activity = activity as Callbacks
    }

    fun setSong(song:Song){
        mp.reset()
        mp = MediaPlayer.create(this,song.audio)
        curSong = song
        mp.setOnCompletionListener {
            applicationContext.sendBroadcast(Intent("TRACKS_TRACK").putExtra("actioname",SoundNotification.ACTION_NEXT))
        }
        mp.start()
    }

    fun songState(){
        if(mp.isPlaying){
            mp.pause()
        } else{
            mp.start()
        }
    }

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    interface Callbacks {
        fun updateSong(song:Song)
        fun changeState()
    }
}