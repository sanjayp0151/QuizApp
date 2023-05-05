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

class SignupActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()
        val signUpBtn: Button = findViewById<Button>(R.id.btnSignUp)
        val loginText: TextView = findViewById<TextView>(R.id.textLogin)

        loginText.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        signUpBtn.setOnClickListener {
            signUpUser()
        }

    }
    private fun signUpUser(){
        val etEmail : EditText = findViewById<EditText>(R.id.etREmailAddress)
        val etPassword : EditText = findViewById<EditText>(R.id.etRPassword)
        val etConfirmPassword : EditText  = findViewById<EditText>(R.id.etRConfirmPassword)
        val email : String = etEmail.text.toString()
        val password : String = etPassword.text.toString()
        val confirmPassword : String = etConfirmPassword.text.toString()

        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            Toast.makeText(this,"Email and Password can't be blank",Toast.LENGTH_SHORT).show()
            return
        }

        if(password!=confirmPassword){
            Toast.makeText(this,"Password and ConfirmPassword do not match",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Register successfully",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"error in create new user",Toast.LENGTH_SHORT).show();
                }
            }
    }
}