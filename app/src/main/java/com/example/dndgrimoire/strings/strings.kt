package com.example.dndgrimoire.strings

import android.icu.lang.UCharacter

fun capitalize(strValue: String): String {

    var loweredString = UCharacter.toLowerCase(strValue)
    return UCharacter.toUpperCase(loweredString.substring(0, 1)) + loweredString.substring(1)
}