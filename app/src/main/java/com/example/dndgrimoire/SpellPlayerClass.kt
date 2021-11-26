package com.example.dndgrimoire

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spells_player_classes",
    primaryKeys = ["spellId", "playerClassId"])
data class SpellPlayerClass(
    val spellId: Int,
    val playerClassId: Int
){
//    constructor(spellId: Int, placerClassId: Int)
//            :this(spellId, placerClassId)
}
