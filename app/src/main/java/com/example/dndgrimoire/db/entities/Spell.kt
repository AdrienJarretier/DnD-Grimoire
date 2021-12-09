package com.example.dndgrimoire.db.entities

import androidx.room.*
import com.example.dndgrimoire.strings.*

@Entity(
    tableName = "spells", indices = [Index(
        value = ["spell_name"],
        unique = true
    )]
)
data class Spell
@Ignore
constructor(
    @PrimaryKey(autoGenerate = true) var spellId: Int?,
    @ColumnInfo val spell_name: String,
    @ColumnInfo val level: Int?,
    @ColumnInfo val school: String?,
    @ColumnInfo val cast_time: String?,
    @ColumnInfo val range: String?,
    @ColumnInfo val components: String?,
    @ColumnInfo val duration: String?,
    @ColumnInfo val description: String?
) {

    constructor(
        spell_name: String, level: Int? = null,
        school: String? = null, cast_time: String? = null,
        range: String? = null, components: String? = null,
        duration: String? = null, description: String? = null
    )
            : this(
        null,
        capitalize(spell_name),
        level,
        school,
        cast_time,
        range,
        components,
        duration,
        description
    )

    constructor(spell_params: List<String>) : this(
        spell_params[0],
        spell_params[1].toInt(),
        spell_params[2],
        spell_params[3],
        spell_params[4],
        spell_params[5],
        spell_params[6],
        spell_params[7]
    )

}
