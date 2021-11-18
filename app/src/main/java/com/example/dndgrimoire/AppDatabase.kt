package com.example.dndgrimoire

import androidx.room.*

@Database(entities = [Spell::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun spellDao(): SpellDao
}
