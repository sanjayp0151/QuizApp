package com.example.quiz.activity.models

data class Quiz(
    var id : String="",
    var title : String="",
    var questions : MutableMap<String,Question> = mutableMapOf()
)
