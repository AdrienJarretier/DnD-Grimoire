package com.example.dndgrimoire

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SpellDao {
        @Query("SELECT * FROM spells")
        fun getAll(): List<Spell>

        @Query("SELECT * FROM spells WHERE id= :id")
        fun get(id: Int): Spell

        @Query("SELECT count(*)==0 FROM spells")
        fun isEmpty(): Boolean

        @Query("SELECT * FROM spells WHERE id IN (:spellIds)")
        fun loadAllByIds(spellIds: IntArray): List<Spell>

        @Query("SELECT * FROM spells WHERE spell_name LIKE :name LIMIT 1")
        fun findByName(name: String): Spell


        @Insert
        fun insertAll(vararg spells: Spell)

        @Insert
        fun insert(spell: Spell)

        @Delete
        fun delete(spell: Spell)
}