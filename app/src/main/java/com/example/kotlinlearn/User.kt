package com.example.kotlinlearn

class User() {
    lateinit var name: String
    lateinit var email: String

    fun User(){

    }

    fun User(_name: String, _email: String){
        this.name = _name
        this.email = _email
    }
}