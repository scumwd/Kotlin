package com.example.firsapp

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.firsapp.services.PlayService
import kotlinx.android.synthetic.main.activity_track_page.*

class TrackPage : AppCompatActivity(),Play, PlayService.Callbacks {
    private lateinit var musicService: PlayService
    lateinit var notificationManager: NotificationManager
    var position: Int = 0
    var isPlaying: Boolean = true

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val service = savedInstanceState["binder"]
        val binder: PlayService.LocalBinder = service as PlayService.LocalBinder
        musicService = binder.getServiceInstance()
        musicService.registerClient(this@TrackPage)
        super.onRestoreInstanceState(savedInstanceState)
    }

    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder: PlayService.LocalBinder = service as PlayService.LocalBinder
            musicService = binder.getServiceInstance()
            musicService.registerClient(this@TrackPage)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }
    lateinit var songList: List<Song>
    lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_page)
        iv_play.setOnClickListener {
            if (isPlaying) {
                onTrackPause()
            } else {
                onTrackPlay()
            }
        }
        iv_next.setOnClickListener {
            onTrackNext()
        }
        iv_back.setOnClickListener {
            onTrackBack()
        }

    }

    private fun initPage() {
        tv_author.text = song.author
        tv_title.text = song.title
        iv_poster.setImageResource(song.cover)
    }

    override fun onStart() {
        super.onStart()
        val source = intent.getStringExtra("source")
        songList = SongRepository.getSong()
        if (source == "notification") {
            song = intent.getSerializableExtra("song") as Song
            position = SongRepository.getPosition(song.author, song.title)
            val intentService = Intent(baseContext, PlayService::class.java)
            bindService(intentService, serviceConnection, Context.BIND_AUTO_CREATE)
            intent.removeExtra("source")
            intent.removeExtra("position")
        } else {
            position = intent.getIntExtra("position", 0)
            song = songList[position]
            if(isMusicServiceRunning(PlayService::class.java)) {
                song = musicService.curSong
                position = SongRepository.getPosition(song.author,song.title)
            }
            createChannel()

        }
        initPage()
        val intentService = Intent(baseContext, PlayService::class.java)
        if (!isMusicServiceRunning(PlayService::class.java)) {
            intentService.putExtra("song", song)
            startService(intentService)
            bindService(intentService, serviceConnection, Context.BIND_AUTO_CREATE)
            registerReceiver(broadcastReceiver, IntentFilter("TRACKS_TRACK"))
            SoundNotification.createNotification(
                this, song,isPlaying
            )
        }
    }

    private fun createChannel() {
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
    }

    val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                when (intent.getStringExtra("actioname")) {
                    SoundNotification.ACTION_NEXT -> {
                        onTrackNext()
                    }
                    SoundNotification.ACTION_PLAY -> {
                        if (isPlaying) {
                            onTrackPause()
                        } else onTrackPlay()
                    }

                    SoundNotification.ACTION_DELETE -> {
                        clearAll()
                    }

                    SoundNotification.ACTION_PREVIOUS -> {
                        onTrackBack()
                    }
                }
            }
        }
    }

    private fun clearAll() {
        notificationManager.cancelAll()
        unbindService(serviceConnection)
        val intentService = Intent(baseContext, PlayService::class.java)
        stopService(intentService)
        unregisterReceiver(broadcastReceiver)
        onDestroy()
    }

    override fun onTrackBack() {
        position--
        if (position < 0) {
            position = songList.size - 1
        }
        song = songList[position]
        initPage()
        updateSong(song)
        SoundNotification.createNotification(
            this, song,isPlaying
        )
    }

    override fun onTrackPlay() {
        changeState()
        isPlaying = true
        SoundNotification.createNotification(
            this, song,isPlaying
        )
        iv_play.setImageResource(R.drawable.ic_stop)
    }

    override fun onTrackPause() {
        changeState()
        isPlaying = false
        SoundNotification.createNotification(
            this, songList[position],isPlaying
        )

        iv_play.setImageResource(R.drawable.ic_play)
    }

    override fun onTrackNext() {
        position++
        if (position >= songList.size) {
            position = 0
        }
        song = songList[position]
        initPage()
        updateSong(song)
        SoundNotification.createNotification(
            this, song,isPlaying
        )
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun updateSong(song: Song) {
        musicService.setSong(song)
        iv_play.setImageResource(R.drawable.ic_stop)
        isPlaying = true
    }

    override fun changeState() {
        musicService.songState()
    }

    private fun isMusicServiceRunning(serviceClass: Class<PlayService>): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        manager.getRunningServices(Integer.MAX_VALUE).forEach {
            if (serviceClass.name == it.service.className) {
                return true
            }
        }
        return false
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        initPage()
        super.onResume()
    }
}