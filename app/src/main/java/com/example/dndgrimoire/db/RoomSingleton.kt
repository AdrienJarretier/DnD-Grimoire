package com.example.dndgrimoire.db

import android.content.Context
import androidx.room.*

const val DATABASE_VERSION = 2

@Database(entities = [Spell::class, PlayerClass::class, SpellPlayerClass::class],
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