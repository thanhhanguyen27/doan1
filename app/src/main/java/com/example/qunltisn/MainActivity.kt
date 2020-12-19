package com.example.qunltisn

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pref= getSharedPreferences(SHARED_NAME, MODE_PRIVATE)
        val usn=pref.getString(KEY_USERNAME, "")
        val token=pref.getString(KEY_TOKEN, "")

        if ((usn != "") && (token != "")) {
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        }
    }
}