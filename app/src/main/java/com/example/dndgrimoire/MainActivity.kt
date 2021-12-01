package com.example.dndgrimoire

import android.app.AlertDialog
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.dndgrimoire.db.RoomSingleton

import testInsert.*
import android.content.DialogInterface

import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val spellDao = RoomSingleton.getInstance(applicationContext).spellDao()
        if(spellDao.isEmpty()) {
            Log.d("isEmpty", "true")

            try {
                TestInsert2.testInsert(spellDao)
            }catch (e: SQLiteConstraintException) {

                val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
                alertDialog.setTitle("Error")
                alertDialog.setMessage(e.localizedMessage)
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })
                alertDialog.show()
                RoomSingleton.getInstance(applicationContext).clearAllTables()
            }

        }else {
            Log.d("isEmpty", "false")
        }


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)

        setSupportActionBar(findViewById(R.id.toolbar))

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.menu, menu)
//        return true
//    }

}