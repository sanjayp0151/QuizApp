package com.example.quiz.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.quiz.R
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        val getStartedBtn: Button = findViewById<Button>(R.id.btnGetStarted)
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
            Toast.makeText(this,"Already logged in", Toast.LENGTH_SHORT).show()
            redirected("Main")
        }

        getStartedBtn.setOnClickListener {
            redirected("Login")
        }
    }
    private fun redirected(name : String){
        val intent : Intent = when(name){
            "Login" -> Intent(this, LoginActivity::class.java)
            "Main" ->Intent(this, MainActivity::class.java)
            else ->throw Exception("NO path exists")
        }
        startActivity(intent)
        finish()
    }
}