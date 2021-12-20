package com.easyapp.hero_es.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.easyapp.hero_es.databinding.ActivitySplashBinding
import com.easyapp.hero_es.viewModel.HeroViewModel

class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel by viewModels<HeroViewModel> ()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root).apply {

            Handler().postDelayed(Runnable {
                startActivity(
                    Intent(
                        this@Splash,
                        MainActivity::class.java
                    )
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                finish()
            }, 5000)

        }
    }
}