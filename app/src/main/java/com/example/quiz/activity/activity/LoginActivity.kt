package com.example.quiz.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quiz.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        val loginBtn: Button = findViewById<Button>(R.id.btnLogin)
        val signUpText: TextView = findViewById<TextView>(R.id.textSignup)

        signUpText.setOnClickListener(){
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginBtn.setOnClickListener {
            loginUser()
        }

    }
    private fun loginUser(){
        val etEmail : EditText = findViewById<EditText>(R.id.etEmailAddress)
        val etPassword : EditText = findViewById<EditText>(R.id.etPassword)
        val email : String = etEmail.text.toString()
        val password : String = etPassword.text.toString()

        if(email.isBlank() || password.isBlank() ){
            Toast.makeText(this,"Email and Password can't be blank",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(){
            if(it.isSuccessful){
                Toast.makeText(this,"Login successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Authentication failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}