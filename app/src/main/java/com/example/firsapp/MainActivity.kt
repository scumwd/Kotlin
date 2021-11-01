package com.example.firsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.firsapp.databinding.ActivityMainBinding
import android.content.Intent
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var userAdapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(UserRepository.users, Glide.with(this)){
            val intent = Intent(
                this,
                SecondActivity::class.java
            )
            intent.putExtra("ID", it)
            startActivity(intent)
        }
        findViewById<RecyclerView>(binding.rvUser.id).run {
            adapter = userAdapter
        }

    }
}
