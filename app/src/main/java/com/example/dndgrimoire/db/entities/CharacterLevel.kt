package com.example.dndgrimoire.db.entities

import androidx.room.*

@Entity
data class CharacterLevel
constructor(
    @PrimaryKey val characterLevelValue: Int
)