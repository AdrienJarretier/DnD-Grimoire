package com.example.dndgrimoire

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dndgrimoire.db.RoomSingleton
import com.example.dndgrimoire.db.Spell


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [spells_list.newInstance] factory method to
 * create an instance of this fragment.
 */
class spells_list : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_spells_list, container, false)

        val db = RoomSingleton.getInstance(requireContext())

        val spellDao = db.spellDao()

        val preferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        val linearVertLayout = rootView.findViewById<LinearLayout>(R.id.linearVerticalLayout)

        fun addSpells(spells: List<Spell>) {

            fun addTextLevelTitle(text: String) {
                val textLevelTitle = TextView(context)
                textLevelTitle.text = text
                textLevelTitle.setTextAppearance(R.style.SpellLevelTitle)
                linearVertLayout.addView(textLevelTitle)
            }

//            val spells = spells.sortedWith(compareBy(Spell::level, Spell::spell_name))

            Log.i("addSpells", "addSpells")
            spells.forEach { s -> Log.i("s:", s.spell_name) }

            val spells = spells.sortedWith(object : Comparator<Spell> {
                override fun compare(o1: Spell, o2: Spell): Int {
                    return if (o1.level == o2.level)
                        0
                    else if (o2.level == null)
                        -1
                    else if (o1.level == null)
                        1
                    else if (o1.level < o2.level)
                        -1
                    else
                        1
                }

            })

            addTextLevelTitle("Tours de magie")
            var spellLevel: String? = "0"

            for (spell in spells) {

                if (spellLevel.toString() != spell.level.toString()) {
                    spellLevel = spell.level.toString()
                    addTextLevelTitle("Niveau $spellLevel")
                }

                val tv = TextView(context)

                tv.text = spell.spell_name
//                tv.setBackgroundColor(
//                    resources.getColor(
//                        R.color.purple_500,
//                        resources.newTheme()
//                    )
//                )
                tv.setOnClickListener {

                    val action = spells_listDirections.actionSpellsListToSpellCard(spell.spellId!!)
                    findNavController().navigate(action)

                }

                val mlp = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                mlp.bottomMargin = (convertDpToPx(requireContext(), 8))

                tv.height = convertDpToPx(requireContext(), 48)
                tv.layoutParams = mlp
                tv.gravity = Gravity.CENTER_VERTICAL

                linearVertLayout.addView(tv)
            }
        }

        try {

            val spells: List<Spell> = spellDao.getSpellsForCharacterClass(
                preferences.getString(
                    "characterClass",
                    "Classe"
                )!!
            ).spells

            addSpells(spells)

        } catch (npe: NullPointerException) {

            for (characterClass in spellDao.getPlayerClassesWithSpells()) {
                val text = TextView(context)
                text.text = characterClass.playerClass.name + " :"
                text.setTextAppearance(R.style.PlayerClassNameSeparator)
                linearVertLayout.addView(text)
                addSpells(characterClass.spells)
            }
        }

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment spells_list.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            spells_list().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}