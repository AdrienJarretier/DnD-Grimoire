package com.example.dndgrimoire

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PlayerClassWithSpells(
    @Embedded val playerClass: PlayerClass,
    @Relation(
        parentColumn = "playerClassId",
        entityColumn = "spellId",
        associateBy = Junction(SpellPlayerClass::class)
    )
    val spells: List<Spell>
)
