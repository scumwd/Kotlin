package com.example.firsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.firsapp.databinding.ActivityMainBinding
import com.example.firsapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var id = intent.getSerializableExtra("ID")
        val user: User = UserRepository.users.find { it.id ==id }!!
        with(binding){
            Glide.with(this@SecondActivity).load(user.url).into(ivPhoto)
            tvIddd.text = "ID: ${user.id}"
            tvName.text = "Name: ${user.name}"
            tvDiscrip.text = "Discription: ${user.discrip}"
        }
    }
}
