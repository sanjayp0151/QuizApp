package com.example.quiz.activity.utils

import com.example.quiz.R

object IconPicker {
    val icon = arrayOf(R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4)
    var currentIcon =0

    fun getIcon(): Int {
        currentIcon = (currentIcon+1)% icon.size
        return icon[currentIcon]
    }
}