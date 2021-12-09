package com.example.dndgrimoire.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["spellId", "characterClassId"])
data class SpellCharacterClass(
    val spellId: Int,
    val characterClassId: Int
){
//    constructor(spellId: Int, placerClassId: Int)
//            :this(spellId, placerClassId)
}
