package com.example.kotlinlearn

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {


    private lateinit var button: Button
    private lateinit var toolbar: Toolbar
    private lateinit var auth: FirebaseAuth
    private lateinit var imageBackGray: ImageView
    private lateinit var animation: Animation
    private var isTrue = true
    private lateinit var recyclerview: RecyclerView
    private lateinit var cardViewAddMoney: CardView
    private lateinit var cardViewOkMoney: CardView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recyclerViewShow()

        cardViewAddMoney = findViewById(R.id.cardViewAddMoney)
        cardViewOkMoney = findViewById(R.id.cardViewOkMoney)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        auth = FirebaseAuth.getInstance()
        imageBackGray = findViewById(R.id.imageBackGray)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setTitle(null)

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
                builder.setTitle("??????????")
                //set message for alert dialog
                builder.setMessage("???? ?????????????????????????? ???????????? ???????????")

                //performing positive action
                builder.setPositiveButton("????"){dialogInterface, which ->
                    auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()                }
                //performing cancel action
                builder.setNeutralButton("????????????"){dialogInterface , which ->
                    Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
                }
                //performing negative action
                builder.setNegativeButton("??????"){dialogInterface, which ->
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

    fun backGround(view: View) {
        if (isTrue == true){
            animation = AnimationUtils.loadAnimation(this, R.anim.translate)
            imageBackGray.startAnimation(animation)
            imageBackGray.visibility = View.GONE
            isTrue = false
        }else {
            animation = AnimationUtils.loadAnimation(this, R.anim.translate_vis)
            imageBackGray.visibility = View.VISIBLE
            imageBackGray.startAnimation(animation)
            isTrue = true
        }

    }

    fun floatButtonAddMoney(view: View) {
        imageBackGray.visibility = View.VISIBLE
        cardViewAddMoney.visibility = View.GONE
        cardViewOkMoney.visibility = View.VISIBLE
    }
    fun floatButtonOkMoney(view: View) {
        imageBackGray.visibility = View.GONE
        cardViewAddMoney.visibility = View.VISIBLE
        cardViewOkMoney.visibility = View.GONE
    }

//    fun cardViewOkMoney(view: View) {}
//    fun recyclerViewShow(){
//        recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
//        recyclerview.layoutManager = LinearLayoutManager(this)
//        val data = ArrayList<ItemsViewModel>()
//        val adapter = CustomAdapter(data)
//        recyclerview.adapter = adapter
//
//    }

}