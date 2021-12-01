package com.example.dndgrimoire

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.dndgrimoire.db.PlayerClass
import com.example.dndgrimoire.db.RoomSingleton
import java.lang.NumberFormatException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SetPlayerSheet.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetPlayerSheet : Fragment() {
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

        val rootView = inflater.inflate(R.layout.fragment_set_player_sheet, container, false)

        val db = RoomSingleton.getInstance(requireContext())
        val spellDao = db.spellDao()

        val preferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        /**************************************************/
        /***************** Character Name *****************/
        /**************************************************/
        val characterNameEditText = rootView.findViewById<EditText>(R.id.editTextTextPersonName)
        characterNameEditText.setText(preferences.getString("characterName", ""))
        characterNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("name changed", characterNameEditText.text.toString())
                with(preferences.edit()) {
                    putString(
                        "characterName",
                        characterNameEditText.text.toString()
                    )
                    apply()
                }
            }

        })
        /**************************************************/
        /**************************************************/


        /*************************************************/
        /*************** Character Classes ***************/
        /*************************************************/
        var characterClasses: List<String> = listOf("Classe") +
                spellDao.getAllCharacterClasses().sortedWith(compareBy(PlayerClass::name))
                    .map { charClass -> charClass.name }

        val savedCharacterClass = preferences.getString(
            "characterClass",
            "Classe"
        )!!
        Log.i(
            "saved class : ",
            savedCharacterClass
        )

//        val indexOfClassInCharacterClasses = characterClasses.indexOf(
//            savedCharacterClass
//        )
//        Log.i(
//            "indexOfClassInCharacterClasses : ",
//            indexOfClassInCharacterClasses.toString()
//        )

        val spinnerClassesSelector = rootView.findViewById<Spinner>(R.id.characterClassSelector)

        ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item, characterClasses
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerClassesSelector.adapter = adapter

            spinnerClassesSelector.setSelection(
                adapter.getPosition(savedCharacterClass)
            )
        }
        spinnerClassesSelector.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // An item was selected. You can retrieve the selected item using

                    Log.d("characterClass changed", parent?.getItemAtPosition(position).toString())
                    with(preferences.edit()) {
                        putString(
                            "characterClass",
                            parent?.getItemAtPosition(position).toString()
                        )
                        apply()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        /*************************************************/
        /*************************************************/


        /*************************************************/
        /**************** Character Level ****************/
        /*************************************************/
        val characterLevelEditText = rootView.findViewById<EditText>(R.id.editTextCharacterLevel)
        characterLevelEditText.setText(preferences.getInt("characterLevel", 0).toString())
        characterLevelEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                val level = try {
                    val levelString = characterLevelEditText.text.toString()
                    Log.d("level changed", levelString)
                    levelString.toInt()
                } catch (ne: NumberFormatException) {
                    Log.d("level changed", "0")
                    0
                }

                with(preferences.edit()) {
                    putInt(
                        "characterLevel", level
                    )
                    apply()
                }
            }

        })
        /*************************************************/
        /*************************************************/

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SetPlayerSheet.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SetPlayerSheet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}