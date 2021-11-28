package com.example.dndgrimoire

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.dndgrimoire.db.RoomSingleton

import testInsert.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val spellDao = RoomSingleton.getInstance(applicationContext).spellDao()
        if(spellDao.isEmpty()) {
            Log.d("isEmpty", "true")
            TestInsert2.testInsert(spellDao)
        }else {
            Log.d("isEmpty", "false")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

}