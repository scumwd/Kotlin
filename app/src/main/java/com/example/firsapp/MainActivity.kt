package com.example.firsapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    private val adapter= PhotoAdapter()
    private val imageIdList= listOf(R.drawable.photo1,
        R.drawable.photo2,
        R.drawable.photo3,
        R.drawable.photo4
    )
    private var index = 0
    private val REQEST_TAKE_PHOTO = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        binding.etName.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) { binding.etName.clearFocus()
                    binding.etName.isCursorVisible = false
                    return true
                }
                return false
            }
        })
        //intent
        binding.ibTakepic.setOnClickListener {
            val takePicIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicIntent,REQEST_TAKE_PHOTO)
        }

        if (intent?.action==Intent.ACTION_SEND){
            setPick(intent)
        }
    }

    private  fun setPick(intent: Intent){
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let{
            val avatar = Drawable.createFromStream(contentResolver.openInputStream(it), it.toString())
            binding.ivAvatar.setImageDrawable(avatar)
            binding.ivAvatarmini.setImageDrawable(avatar)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQEST_TAKE_PHOTO && resultCode== RESULT_OK){
            (data?.extras?.get("data") as? Bitmap)?.let {
                binding.ivAvatar.setImageBitmap(it)
                binding.ivAvatarmini.setImageBitmap(it)
            }
        }
    }


    private fun init(){
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity,3)
            rcView.adapter = adapter
            ibAddp.setOnClickListener{
                if (index>3) index = 0
                val photo = Photos(imageIdList[index])
                adapter.addPhoto(photo)
                var g = adapter.itemCount
                tvPublications.text = "${g}\nПубликации"
                index++
            }
        }
    }
}
