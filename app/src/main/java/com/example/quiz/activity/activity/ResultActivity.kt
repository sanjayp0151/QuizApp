package com.example.quiz.activity.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.example.quiz.R
import com.example.quiz.activity.models.Question
import com.example.quiz.activity.models.Quiz
import com.google.gson.Gson
import java.lang.StringBuilder

class ResultActivity : AppCompatActivity() {
    lateinit var quiz :Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpView()
    }

    private fun setUpView() {
        val quizData :String? = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData,Quiz::class.java)//deserialize

        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {
        var txtAnswer = findViewById<TextView>(R.id.txtAnswer)
        val builder = StringBuilder("")
        for (entry : MutableMap.MutableEntry<String,Question> in quiz.questions.entries){
            val question : Question = entry.value
            builder.append("<font color '#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color '#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            txtAnswer.text = Html.fromHtml(builder.toString(),Html.FROM_HTML_MODE_COMPACT)
        }else{
            txtAnswer.text=Html.fromHtml(builder.toString())
        }
    }

    private fun calculateScore() {
        var score =0
        for(entry :MutableMap.MutableEntry<String,Question> in quiz.questions.entries){
            val question:Question =entry.value
            if(question.answer==question.userAnswer){
                score+=10
            }
        }
        var txtScore = findViewById<TextView>(R.id.txtScore)
        txtScore.text = "Your Score : $score "
    }
}