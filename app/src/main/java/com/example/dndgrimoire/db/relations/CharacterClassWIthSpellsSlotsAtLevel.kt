package com.example.dndgrimoire.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.dndgrimoire.db.entities.CharacterClass
import com.example.dndgrimoire.db.entities.Spell
import com.example.dndgrimoire.db.entities.SpellCharacterClass
import com.example.dndgrimoire.db.entities.SpellLevel

data class CharacterClassWIthSpellsSlotsAtLevel(
    @Embedded val characterClass: CharacterClass,
    @Relation(
        parentColumn = "characterClassId",
        entityColumn = "spellId",
        associateBy = Junction(SpellCharacterClass::class)
    )
    val spellsSlots: Map<SpellLevel, Int>
)
