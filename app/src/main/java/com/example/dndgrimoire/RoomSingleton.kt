package com.example.dndgrimoire

import android.content.Context
import androidx.room.*

@Database(entities = [Spell::class], version = 1)
abstract class RoomSingleton : RoomDatabase(){
    abstract fun spellDao():SpellDao

    companion object{
        private var INSTANCE: RoomSingleton? = null
        fun getInstance(context:Context): RoomSingleton{
            if (INSTANCE == null){

                val builder = Room.databaseBuilder(
                    context,
                    RoomSingleton::class.java,
                    "roomdb")

                builder.allowMainThreadQueries()

                INSTANCE = builder.build()
            }

            return INSTANCE as RoomSingleton
        }
    }
}