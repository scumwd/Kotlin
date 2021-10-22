package com.example.firsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.firsapp.databinding.ActivityMainBinding
import android.R




class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var frag1: LikeFragment = LikeFragment.newIstance()
    var frag2: VideoFragment = VideoFragment.newIstance()
    var frag3: MenuFragment = MenuFragment.newIstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivLike.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
                .replace(binding.frC.id , frag1)
                .addToBackStack("Like")
                .commit()
        }

        binding.ivVideo.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
                .replace(binding.frC.id,frag2)
                .addToBackStack("Video")
                .commit()}

        binding.ivMenu.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
                .replace(binding.frC.id, frag3)
                .addToBackStack("Menu")
                .commit()
        }
    }

}
