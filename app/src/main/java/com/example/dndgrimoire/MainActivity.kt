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
import android.view.MenuItem

import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

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

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val appBarConfiguration = AppBarConfiguration(
            navController.graph, drawerLayout
        )
        findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)

        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)



    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment)
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//    }


}