package com.example.geoquizlifecycle

import android.widget.Toast
import androidx.lifecycle.ViewModel

class QuestionViewModel :  ViewModel() {
    var currentIndex = 0
    private var count = 0
    private var answerList = mutableListOf<Int>()
    private var score: Int? = null
    private var isButtonVisible = true



    val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
        //setVisibility()
    }

    fun movePrev() {
        currentIndex = (currentIndex + 1) % questionBank.size
        //setVisibility()
    }

    fun trackQuestionsAnswered() {
        if (currentIndex !in answerList) {
            answerList.add(currentIndex)
        }
    }

// this is the function I am referring to
    private fun setVisibility() {
        if (answerList.size == 6) {
            isButtonVisible = false
            score = (count * 100) / answerList.size
            Toast.makeText(this, "All 6 Questions Completed", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Your score is $score %", Toast.LENGTH_SHORT).show()
        } else if (currentIndex in answerList) {
            isButtonVisible = false
            updateQuestion()
        } else {
            isButtonVisible = true
            updateQuestion()
        }
    }

    fun updateCorrectCount() {
       count++
    }
}