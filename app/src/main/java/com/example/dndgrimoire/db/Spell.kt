package com.example.dndgrimoire.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spells")
data class Spell(
    @PrimaryKey(autoGenerate = true) val spellId: Int?,
    @ColumnInfo val spell_name: String,
    @ColumnInfo val level: Int,
    @ColumnInfo val school: String,
    @ColumnInfo val cast_time: String,
    @ColumnInfo val range: String,
    @ColumnInfo val components: String,
    @ColumnInfo val duration: String,
    @ColumnInfo val description: String
){
    constructor(spell_name: String,level: Int,
                school: String,cast_time: String,
                range: String,components: String,
                duration: String,description: String)
            :this(null, spell_name, level, school, cast_time, range, components, duration, description)
}
