package com.example.tipcalculator.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.tipcalculator.MainActivity
import com.example.tipcalculator.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            val myintent= Intent(this@SplashScreen,MainActivity::class.java)
            startActivity(myintent)
        },3000)
    }
}