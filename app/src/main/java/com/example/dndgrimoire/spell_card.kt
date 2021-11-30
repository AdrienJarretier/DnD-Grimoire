package com.example.dndgrimoire

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dndgrimoire.db.RoomSingleton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [spell_card.newInstance] factory method to
 * create an instance of this fragment.
 */
class spell_card : Fragment() {
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

        val rootView = inflater.inflate(R.layout.fragment_spell_card, container, false)

        val listButton = rootView.findViewById<Button>(R.id.list_button)
        listButton.setOnClickListener{

            findNavController().navigate(R.id.action_spell_card_to_spells_list)

        }

        return rootView
    }

        val args: spell_cardArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val spellId = args.spellId

        Log.d("spellId", spellId.toString())

        val db = RoomSingleton.getInstance(requireContext())
        val spellDao = db.spellDao()

        val spell = spellDao.getSpell(spellId)


        view.findViewById<TextView>(R.id.spellName).text = spell.spell_name

        val subtitle = getString(R.string.subtitle)
        val levelValue = spell.level
        val magicSchool = spell.school
        val subtitleTextView = view.findViewById<TextView>(R.id.subtitle)
        subtitleTextView.text = String.format(subtitle, levelValue, magicSchool)

        view.findViewById<TextView>(R.id.castTimeValue).text = spell.cast_time

        view.findViewById<TextView>(R.id.rangeValue).text = spell.range

        view.findViewById<TextView>(R.id.componentsValue).text = spell.components

        view.findViewById<TextView>(R.id.durationValue).text = spell.duration

        val descriptionTextView = view.findViewById<TextView>(R.id.description)
        descriptionTextView.text = spell.description
        descriptionTextView.typeface = Typeface.SANS_SERIF
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment spell_card.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            spell_card().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}