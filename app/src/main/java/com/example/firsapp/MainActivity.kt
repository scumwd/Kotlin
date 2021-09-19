package com.example.firsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val student = FirstChild("Artem","11-012").line
        println(student)
        val phone = SecondChild("Artem","+7932326313").line
        println(phone)
    }
}
