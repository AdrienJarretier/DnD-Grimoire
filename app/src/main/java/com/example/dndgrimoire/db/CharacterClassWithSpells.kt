package com.example.dndgrimoire.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CharacterClassWithSpells(
    @Embedded val characterClass: CharacterClass,
    @Relation(
        parentColumn = "playerClassId",
        entityColumn = "spellId",
        associateBy = Junction(SpellCharacterClass::class)
    )
    val spells: List<Spell>
)
