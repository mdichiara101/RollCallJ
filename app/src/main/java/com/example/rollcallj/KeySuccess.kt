package com.example.rollcallj

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity

class KeySuccess: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.key_success)
        // Gets saved key from the database
        val preferences = getSharedPreferences("database", Context.MODE_PRIVATE)
        val savedKey = preferences.getString("savedKeyValue", "This value doesn't exist.")
        d("KEY1", "Entered key is: $savedKey")
        startActivity(Intent(this, MainActivity::class.java))
    }
}