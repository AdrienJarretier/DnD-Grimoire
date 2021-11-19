package com.example.dndgrimoire

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val builder = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        )
        builder.allowMainThreadQueries()
        val db = builder.build()

        val spellDao = db.spellDao()


        val spells: List<Spell> = spellDao.getAll()

        Log.w("spells count :", spells.size.toString())
        for (spell in spells) {
            Log.w("spell :", spell.spell_name)
        }



    }
}