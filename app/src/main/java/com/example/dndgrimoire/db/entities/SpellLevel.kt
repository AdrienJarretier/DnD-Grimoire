package com.example.dndgrimoire.db.entities

import androidx.room.*

@Entity
data class SpellLevel
constructor(
    @PrimaryKey val spellLevelValue: Int
)