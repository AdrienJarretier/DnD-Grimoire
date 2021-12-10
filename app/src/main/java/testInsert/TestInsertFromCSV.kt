package testInsert

import com.example.dndgrimoire.db.Spell
import com.example.dndgrimoire.db.SpellDao
import com.example.dndgrimoire.db.SpellInserter
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.InputStream

class TestInsertFromCSV(spellDao: SpellDao) : TestInsertInterface {

    private val spellInserter = SpellInserter(spellDao)

    fun insertSpells(inputStreamSpellsCsvFile: InputStream) {

        csvReader().open(inputStreamSpellsCsvFile) {
            readAllAsSequence().forEachIndexed { i, row: List<String> ->
                if (i > 0) {
                    //Do something
                    println(row) //[a, b, c]
                    if (!row.contains(""))
                        spellInserter.insert(Spell(row))
                }
            }
        }

    }

    fun insertCharacterClassesWithSpells(inputStreamSpellsCsvFile: InputStream) {

        csvReader().open(inputStreamSpellsCsvFile) {
            readAllAsSequence().forEachIndexed { i, row: List<String> ->
                if (i > 0) {
                    //Do something
                    println(row) //[a, b, c]
                    if (!row.contains(""))
                        spellInserter.insertCharacterClassNameWithSpell(row)
                }
            }
        }

    }

    override fun testInsert(spellInserter: SpellInserter) {
        TODO("Not yet implemented")
    }
}