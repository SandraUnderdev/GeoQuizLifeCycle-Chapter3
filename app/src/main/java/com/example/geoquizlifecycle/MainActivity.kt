package com.example.geoquizlifecycle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquizlifecycle.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val questionViewModel: QuestionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTrue.setOnClickListener { _: View ->
            checkAnswer(true)
            questionViewModel.trackQuestionsAnswered()

        }

        binding.btnFalse.setOnClickListener {
            checkAnswer(false)
            questionViewModel.trackQuestionsAnswered()
        }

        binding.questionTextView.setOnClickListener {
            questionViewModel.moveNext()
            updateQuestion()
//            questionViewModel.setVisibility()
        }

        binding.btnNext.setOnClickListener {
            questionViewModel.moveNext()
            updateQuestion()
          //  questionViewModel.setVisibility()
        }

        binding.btnPrev.setOnClickListener {
            questionViewModel.movePrev()
            updateQuestion()
            //  questionViewModel.setVisibility()
        }

        updateQuestion()
    }


    private fun updateQuestion() {
        if (questionViewModel.currentIndex == -1) {
            Log.d(TAG, "Current question index:  $questionViewModel.currentIndex")
            questionViewModel.currentIndex = questionViewModel.questionBank.size - 1
            val questionTextResId = questionViewModel.currentQuestionText
            binding.questionTextView.setText(questionTextResId)
        } else {
            val questionTextResId = questionViewModel.currentQuestionText
            binding.questionTextView.setText(questionTextResId)
        }
    }


    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionViewModel.currentQuestionAnswer
        var messageResId = if (userAnswer == correctAnswer) {
            //  count++
            questionViewModel.updateCorrectCount()
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

}