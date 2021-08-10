package com.example.kotlinlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class RegActivity : AppCompatActivity() {

    private val TAG: String = "RegActivity"

    private lateinit var editTextName: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextEmailAddress: EditText

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)


        editTextName= findViewById(R.id.editTextName)
        editTextPassword= findViewById(R.id.editTextPassword)
        editTextEmailAddress= findViewById(R.id.editTextEmailAddress)

        auth = FirebaseAuth.getInstance()

    }


    fun tapToLog(view: View) {

        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun registerUser(view: View) {
        registerUser()

    }

    private fun registerUser() {
        var name = editTextName.text.toString().trim()
        var emailAddress = editTextEmailAddress.text.toString().trim()
        var password = editTextPassword.text.toString().trim()

        if(name.isEmpty()){
            editTextName.setError("Name is required!")
            editTextName.requestFocus()
            return
        }
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

        auth.createUserWithEmailAndPassword(emailAddress, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
}