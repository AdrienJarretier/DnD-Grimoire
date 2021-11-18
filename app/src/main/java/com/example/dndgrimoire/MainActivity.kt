package com.example.dndgrimoire

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val subtitle = getString(R.string.subtitle)
        val levelValue = resources.getInteger(R.integer.level_value)

        Log.d(levelValue.toString(), levelValue.toString())

        val magicSchool = getString(R.string.magic_school)

        val subtitleTextView = findViewById<TextView>(R.id.subtitle)
        subtitleTextView.text = String.format(subtitle, levelValue, magicSchool)

    }
}