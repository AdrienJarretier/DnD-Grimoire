package com.example.dndgrimoire.db

import androidx.room.*

@Dao
interface SpellDao {
    @Query("SELECT * FROM spells")
    fun getAllSpells(): List<Spell>

    @Query("SELECT * FROM player_classes")
    fun getAllCharacterClasses(): List<CharacterClass>

    @Query("SELECT * FROM spells WHERE spellId= :id")
    fun getSpell(id: Int): Spell

    @Query("SELECT * FROM player_classes WHERE playerClassId= :id")
    fun getPlayerClass(id: Int): CharacterClass

    @Query("SELECT count(*)==0 FROM spells")
    fun isEmpty(): Boolean

//        @Query("SELECT * FROM spells WHERE spellId IN (:spellIds)")
//        fun loadAllByIds(spellIds: IntArray): List<Spell>

    @Query("SELECT * FROM spells WHERE spell_name LIKE :name LIMIT 1")
    fun findSpellByName(name: String): Spell

    @Query("SELECT * FROM player_classes WHERE name LIKE :name LIMIT 1")
    fun findPlayerClassByName(name: String): CharacterClass

    @Transaction
    @Query("SELECT * FROM player_classes where name LIKE :characterClassName")
    fun getSpellsForCharacterClass(characterClassName: String): CharacterClassWithSpells


    @Transaction
    @Query("SELECT * FROM player_classes")
    fun getPlayerClassesWithSpells(): List<CharacterClassWithSpells>

//    @Insert
//    fun insertAll(vararg spells: Spell)
//        @Insert
//        fun insertAll(vararg playerClasses: PlayerClass)
//        @Insert
//        fun insertAll(vararg spells_playerClasses: SpellPlayerClass)

    @Insert
    fun insert(spell: Spell): Long

    @Insert
    fun insert(characterClass: CharacterClass): Long

    @Insert
    fun insert(spells_characterClasses: SpellCharacterClass)

    fun insert(playerClassName: String, spellsNames: List<String>) {

        var playerClass = findPlayerClassByName(playerClassName)
        if (playerClass == null) {
            playerClass = getPlayerClass(insert(CharacterClass(playerClassName)).toInt())
        }
        for (spellName in spellsNames) {

            var spell = findSpellByName(spellName)
            if (spell == null) {
                spell = getSpell(insert(Spell(spellName)).toInt())
            }
            insert(SpellCharacterClass(spell.spellId!!, playerClass.playerClassId!!))
        }
    }

    fun insertCharacterClassNameWithSpell(playerClassName: String, spellName: String) {
        insert(playerClassName, listOf(spellName))
    }

    @Update
    fun update(spell: Spell)

//        @Delete
//        fun delete(spell: Spell)
}