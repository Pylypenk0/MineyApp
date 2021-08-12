package com.example.kotlinlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import androidx.appcompat.widget.Toolbar
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var toolbar: Toolbar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        auth = FirebaseAuth.getInstance()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_LogOut -> {

                val builder = AlertDialog.Builder(this)
                //set title for alert dialog
                builder.setTitle("Выход")
                //set message for alert dialog
                builder.setMessage("Вы действительно хотите выйти?")

                //performing positive action
                builder.setPositiveButton("Да"){dialogInterface, which ->
                    auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()                }
                //performing cancel action
                builder.setNeutralButton("Отмена"){dialogInterface , which ->
                    Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
                }
                //performing negative action
                builder.setNegativeButton("Нет"){dialogInterface, which ->
                    Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
                }
                // Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                // Set other dialog properties
                alertDialog.setCancelable(false)
                alertDialog.show()

            }
            android.R.id.home -> Toast.makeText(this, "Go back", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}