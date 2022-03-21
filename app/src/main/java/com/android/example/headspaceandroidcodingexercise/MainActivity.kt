package com.android.example.headspaceandroidcodingexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.example.headspaceandroidcodingexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        PicsumPhotosApp.picsumPhotosComponent.inject(this)
        setContentView(binding.root)
    }
}