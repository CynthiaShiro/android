package com.example.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        fun donor(view: android.view.View) {
            val intent = Intent(this,donor::class.java)
            startActivity(intent)
        }
        fun recipient(view: android.view.View) {
            val intent = Intent(this,recipient::class.java)
            startActivity(intent)
        }
    }
}