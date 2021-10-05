package com.example.firsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.GridLayout
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        var etName = findViewById<EditText>(R.id.et_name)
        etName.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) { etName.clearFocus()
                    etName.isCursorVisible = false
                    return true
                }
                return false
            }
        })
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
