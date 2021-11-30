package com.example.dndgrimoire.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "player_classes", indices = [Index(value = ["name"],
    unique = true)])
data class PlayerClass(
    @PrimaryKey(autoGenerate = true) val playerClassId: Int?,
    @ColumnInfo val name: String
){
    constructor(name: String)
            :this(null, name)
}
