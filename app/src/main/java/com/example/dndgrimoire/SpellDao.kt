package com.example.dndgrimoire

import androidx.room.*

@Dao
interface SpellDao {
        @Query("SELECT * FROM spells")
        fun getAll(): List<Spell>

        @Query("SELECT * FROM spells WHERE spellId= :id")
        fun get(id: Int): Spell

        @Query("SELECT count(*)==0 FROM spells")
        fun isEmpty(): Boolean

        @Query("SELECT * FROM spells WHERE spellId IN (:spellIds)")
        fun loadAllByIds(spellIds: IntArray): List<Spell>

        @Query("SELECT * FROM spells WHERE spell_name LIKE :name LIMIT 1")
        fun findByName(name: String): Spell

        @Transaction
        @Query("SELECT * FROM player_classes")
        fun getPlayerClassesWithSpells(): List<PlayerClassWithSpells>

        @Insert
        fun insertAll(vararg spells: Spell)
        @Insert
        fun insertAll(vararg playerClasses: PlayerClass)
        @Insert
        fun insertAll(vararg spells_playerClasses: SpellPlayerClass)

        @Insert
        fun insert(spell: Spell)

        @Delete
        fun delete(spell: Spell)
}