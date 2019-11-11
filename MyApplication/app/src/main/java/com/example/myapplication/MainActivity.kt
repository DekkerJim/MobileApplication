package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val False = "F"
    private val True = "T"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        button.setOnClickListener { answerValidation() }
    }

    private fun answerValidation() {
        if (answer.text.toString() == True && answer2.text.toString() == False && answer3.text.toString() == False && answer4.text.toString() == False){
            onAnswersCorrect()
        }
        else{
            onAnswersIncorrect()
        }
    }

    private fun onAnswersIncorrect() {
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show()
    }


    private fun onAnswersCorrect() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
    }
}
