package com.example.dndgrimoire.db

import androidx.room.*
import com.example.dndgrimoire.db.entities.CharacterClass
import com.example.dndgrimoire.db.entities.Spell
import com.example.dndgrimoire.db.entities.SpellCharacterClass
import com.example.dndgrimoire.db.relations.CharacterClassWithSpells

@Dao
interface SpellDao {
    @Query("SELECT * FROM spells")
    fun getAllSpells(): List<Spell>

    @Query("SELECT * FROM CharacterClass")
    fun getAllCharacterClasses(): List<CharacterClass>

    @Query("SELECT * FROM spells WHERE spellId= :id")
    fun getSpell(id: Int): Spell

    @Query("SELECT * FROM CharacterClass WHERE characterClassId= :id")
    fun getCharacterClass(id: Int): CharacterClass

    @Query("SELECT count(*)==0 FROM spells")
    fun isEmpty(): Boolean

//        @Query("SELECT * FROM spells WHERE spellId IN (:spellIds)")
//        fun loadAllByIds(spellIds: IntArray): List<Spell>

    @Query("SELECT * FROM spells WHERE spell_name LIKE :name LIMIT 1")
    fun findSpellByName(name: String): Spell

    @Query("SELECT * FROM CharacterClass WHERE name LIKE :name LIMIT 1")
    fun findCharacterClassByName(name: String): CharacterClass

    @Transaction
    @Query("SELECT * FROM CharacterClass where name LIKE :characterClassName")
    fun getSpellsForCharacterClass(characterClassName: String): CharacterClassWithSpells


    @Transaction
    @Query("SELECT * FROM CharacterClass")
    fun getCharacterClassesWithSpells(): List<CharacterClassWithSpells>

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

    fun insert(characterClassName: String, spellsNames: List<String>) {

        var characterClass = findCharacterClassByName(characterClassName)
        if (characterClass == null) {
            characterClass = getCharacterClass(insert(CharacterClass(characterClassName)).toInt())
        }
        for (spellName in spellsNames) {

            var spell = findSpellByName(spellName)
            if (spell == null) {
                spell = getSpell(insert(Spell(spellName)).toInt())
            }
            insert(SpellCharacterClass(spell.spellId!!, characterClass.characterClassId!!))
        }
    }

    fun insertCharacterClassNameWithSpell(characterClassName: String, spellName: String) {
        insert(characterClassName, listOf(spellName))
    }

    @Update
    fun update(spell: Spell)

//        @Delete
//        fun delete(spell: Spell)
}