package com.example.kotlinlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val TAG: String = "LoginActivity"

    private lateinit var editTextPassword: EditText
    private lateinit var editTextEmailAddress: EditText

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        editTextPassword= findViewById(R.id.editTextPassword)
        editTextEmailAddress= findViewById(R.id.editTextEmailAddress)

        auth = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    fun login(view: View) {
        loginStart()
    }

    private fun loginStart() {
        var emailAddress = editTextEmailAddress.text.toString().trim()
        var password = editTextPassword.text.toString().trim()


        if(emailAddress.isEmpty()){
            editTextEmailAddress.setError("Email address is required!")
            editTextEmailAddress.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            editTextEmailAddress.setError("Please provide valid email!")
            editTextEmailAddress.requestFocus()
            return
        }
        if(password.length < 6) {
            editTextPassword.setError("Min password length should be 6 characters!")
            editTextPassword.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(emailAddress, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }

    }

    fun tapToReg(view: View) {

        startActivity(Intent(this, RegActivity::class.java))

    }
}