package com.example.quiz.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.activity.adapters.OptionAdapter
import com.example.quiz.activity.models.Question
import com.example.quiz.activity.models.Quiz
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {
    var quizzes : MutableList<Quiz>? = null
    var questions : MutableMap<String,Question>? = null
    var index =1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpFirestore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        var btnPrevious =findViewById<Button>(R.id.btnPrevious)
        var btnSubmit=findViewById<Button>(R.id.btnSubmit)
        var btnNext = findViewById<Button>(R.id.btnNext)
        btnPrevious.setOnClickListener{
            index--
            bindView()
        }
        btnNext.setOnClickListener{
            index++
            bindView()
        }
        btnSubmit.setOnClickListener{
            Log.d("FINALQUIZ",questions.toString())
            val intent = Intent(this,ResultActivity::class.java)
            val json :String = Gson().toJson(quizzes!![0])//json serializer
            intent.putExtra("QUIZ",json)
            startActivity(intent)
        }
    }

    private fun setUpFirestore() {
        val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
        var date : String? = intent.getStringExtra("DATE")
        if(date!=null) {
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if(it!=null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindView()
                    }
                }
        }
    }

    private fun bindView() {
        var btnPrevious =findViewById<Button>(R.id.btnPrevious)
        var btnSubmit=findViewById<Button>(R.id.btnSubmit)
        var btnNext = findViewById<Button>(R.id.btnNext)

        btnPrevious.visibility = View.GONE
        btnSubmit.visibility = View.GONE
        btnNext.visibility = View.GONE

        if(index==1){
            btnNext.visibility = View.VISIBLE
        }
        else if(index == questions!!.size  ){
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }
        else{
            btnPrevious.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
        }

        val question : Question? = questions!!["question$index"]
        question?.let{
            val description  = findViewById<TextView>(R.id.description)
            var optionList  = findViewById<RecyclerView>(R.id.optionList)
            description.text = it.description
            val optionAdapter = OptionAdapter(this,it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)
        }



    }
}