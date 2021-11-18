package com.example.dndgrimoire

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "spells")
data class Spell(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo val spell_name: String,
    @ColumnInfo val level: Int,
    @ColumnInfo val school: String,
    @ColumnInfo val cast_time: Int,
    @ColumnInfo val range: Float,
    @ColumnInfo val components: String,
    @ColumnInfo val duration: Int,
    @ColumnInfo val description: String
){
    constructor(spell_name: String,level: Int,
                school: String,cast_time: Int,
                range: Float,components: String,
                duration: Int,description: String)
            :this(null, spell_name, level, school, cast_time, range, components, duration, description)
}
