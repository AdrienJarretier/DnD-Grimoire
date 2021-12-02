package com.example.dndgrimoire.db

import android.database.sqlite.SQLiteConstraintException
import android.util.Log

class SpellInserter(val spellDao: SpellDao) {

    fun insert(spell: Spell) {

        try {

            spellDao.insert(spell)
        } catch (e: SQLiteConstraintException) {

            throw UniqueConstraintException(spell)

        }

    }

    fun insert(playerClassName: String, spellsNames: List<String>) {

        spellDao.insert(playerClassName, spellsNames)

    }

    class UniqueConstraintException(val spell: Spell) : Throwable() {

    }
}