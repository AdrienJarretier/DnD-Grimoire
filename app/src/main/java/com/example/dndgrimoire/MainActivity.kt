package com.example.dndgrimoire

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val subtitle = getString(R.string.subtitle)
        val levelValue = resources.getInteger(R.integer.level_value)

        val magicSchool = getString(R.string.magic_school)

        val subtitleTextView = findViewById<TextView>(R.id.subtitle)
        subtitleTextView.text = String.format(subtitle, levelValue, magicSchool)


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