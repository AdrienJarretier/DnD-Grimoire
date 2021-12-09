package com.example.dndgrimoire.db

import android.content.Context
import androidx.room.*

const val DATABASE_VERSION = 5

@Database(entities = [Spell::class, CharacterClass::class, SpellCharacterClass::class],
    version = DATABASE_VERSION
)
abstract class RoomSingleton : RoomDatabase(){
    abstract fun spellDao(): SpellDao

    companion object{
        private var INSTANCE: RoomSingleton? = null
        fun getInstance(context:Context): RoomSingleton {
            if (INSTANCE == null){

                val builder = Room.databaseBuilder(
                    context,
                    RoomSingleton::class.java,
                    "roomdb")

                builder.allowMainThreadQueries()
                builder.fallbackToDestructiveMigration()

                INSTANCE = builder.build()
            }

            return INSTANCE as RoomSingleton
        }
    }
}