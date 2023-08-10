package com.example.androidkotlin_espresso

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var mInputText: EditText
    lateinit var validateButton: Button
    lateinit var mSuccessView: TextView
    lateinit var mErrorView: TextView

    enum class SOFT_DRINKS {
        espresso, latte, mocha, coldbrew
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mInputText = findViewById(R.id.editText)
        validateButton = findViewById(R.id.button)
        mSuccessView = findViewById(R.id.inputValidationSuccess)
        mErrorView = findViewById(R.id.inputValidationError)

        validateButton.setOnClickListener {
            val inputText = mInputText.text.toString()
            showResult(validateText(inputText))
        }
    }

    fun showResult(isValidResult: Boolean) {
        mSuccessView.visibility = if (isValidResult) View.VISIBLE else View.GONE
        mErrorView.visibility = if (isValidResult) View.GONE else View.VISIBLE
    }

    private fun validateText(inputText: String): Boolean {
        for (preparation in SOFT_DRINKS.values()) {
            if (preparation.toString().equals(inputText.toLowerCase())) {
                return true
            }
        }
        return false
    }
}