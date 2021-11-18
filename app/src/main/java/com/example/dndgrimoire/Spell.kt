package com.example.dndgrimoire

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "spells")
data class Spell(
    @PrimaryKey val id: Int,
    @ColumnInfo val spell_name: String,
    @ColumnInfo val level: Int,
    @ColumnInfo val school: String,
    @ColumnInfo val cast_time: Int,
    @ColumnInfo val range: Float,
    @ColumnInfo val components: String,
    @ColumnInfo val duration: Int,
    @ColumnInfo val description: String
)