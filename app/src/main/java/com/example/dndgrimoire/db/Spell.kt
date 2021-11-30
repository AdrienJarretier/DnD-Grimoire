package com.example.dndgrimoire.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.dndgrimoire.strings.*

@Entity(tableName = "spells", indices = [Index(value = ["spell_name"],
    unique = true)])
data class Spell(
    @PrimaryKey(autoGenerate = true) val spellId: Int?,
    @ColumnInfo val spell_name: String,
    @ColumnInfo val level: Int?,
    @ColumnInfo val school: String?,
    @ColumnInfo val cast_time: String?,
    @ColumnInfo val range: String?,
    @ColumnInfo val components: String?,
    @ColumnInfo val duration: String?,
    @ColumnInfo val description: String?
){
    constructor(spell_name: String,level: Int? = null,
                school: String? = null,cast_time: String? = null,
                range: String? = null,components: String? = null,
                duration: String? = null,description: String? = null)
            :this(null, capitalize(spell_name), level, school, cast_time, range, components, duration, description)

}
