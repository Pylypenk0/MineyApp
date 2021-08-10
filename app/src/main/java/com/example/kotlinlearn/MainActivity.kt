package com.example.kotlinlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.buttonSingOut)

        auth = FirebaseAuth.getInstance()

    }

    fun singout(view: View) {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}