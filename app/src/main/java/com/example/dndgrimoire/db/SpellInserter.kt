package com.example.dndgrimoire.db

import android.database.sqlite.SQLiteConstraintException
import android.util.Log

class SpellInserter(val spellDao: SpellDao) {

    fun insert(newSpell: Spell) {



        Log.i("newspell desc :", newSpell.description.toString())

        try {
            spellDao.insert(newSpell)
        } catch (e: SQLiteConstraintException) {

            val spellInDb = spellDao.findSpellByName(newSpell.spell_name)
//            Log.d("newSpell :", newSpell.toString())
//            Log.d("spellInDb :", spellInDb.toString())
            if (spellInDb.level == null) {
                newSpell.spellId = spellInDb.spellId
                spellDao.update(newSpell)
            } else
                throw UniqueConstraintException(newSpell)
        }
    }

    fun insert(characterClassName: String, spellsNames: List<String>) {

        spellDao.insert(characterClassName, spellsNames)
    }

    fun insertAll(vararg spells: Spell) {
        for (spell in spells) {
            insert(spell)
        }
    }

    fun insertCharacterClassNameWithSpell(row: List<String>) {

        spellDao.insertCharacterClassNameWithSpell(row[0], row[1])

    }

    class UniqueConstraintException(val spell: Spell) : Throwable()
}