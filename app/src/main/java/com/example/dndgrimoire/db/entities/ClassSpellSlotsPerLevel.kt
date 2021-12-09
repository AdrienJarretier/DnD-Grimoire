package com.example.dndgrimoire.db.entities

import androidx.room.Entity

@Entity(primaryKeys = ["characterClassId", "characterLevelValue", "spellLevelValue"])
data class ClassSpellSlotsPerLevel(
    val characterClassId: Int,
    val characterLevelValue: Int,
    val spellLevelValue: Int,
    val quantity: Int
)