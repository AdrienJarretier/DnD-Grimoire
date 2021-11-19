package com.example.dndgrimoire

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController

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

        val spells: List<Spell> = spellDao.getAll().sortedWith(compareBy(Spell::level, Spell::spell_name))

        val linearVertLayout = rootView.findViewById<LinearLayout>(R.id.linearVerticalLayout)

        Log.w("spells count :", spells.size.toString())
        for (spell in spells) {
            Log.w("spell :", spell.spell_name)
            val text = TextView(context)
            text.text = spell.spell_name
            text.height = 90
            text.setOnClickListener{

                val action = spells_listDirections.actionSpellsListToSpellCard(spell.id!!)
                findNavController().navigate(action)

            }
            linearVertLayout.addView(text)
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