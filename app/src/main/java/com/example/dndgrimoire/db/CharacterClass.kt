package com.example.dndgrimoire.db

import androidx.room.*
import com.example.dndgrimoire.strings.*

@Entity(tableName = "player_classes", indices = [Index(value = ["name"],
    unique = true)])
data class CharacterClass

    @Ignore
    constructor(
        @PrimaryKey(autoGenerate = true) var characterClassId: Int?,
        @ColumnInfo val name: String
){
    constructor(name: String)
            :this(null, capitalize(name))
}
