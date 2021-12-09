package com.example.dndgrimoire.db.entities

import androidx.room.Entity

@Entity(primaryKeys = ["spellId", "characterClassId"])
data class SpellCharacterClass(
    val spellId: Int,
    val characterClassId: Int
){
//    constructor(spellId: Int, placerClassId: Int)
//            :this(spellId, placerClassId)
}
