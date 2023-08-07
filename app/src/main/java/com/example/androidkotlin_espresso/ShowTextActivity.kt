package com.example.androidkotlin_espresso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ShowTextActivity : AppCompatActivity() {
    lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_text)

        resultText = findViewById(R.id.resultText)

        val result = intent.getStringExtra("text")
        resultText.text = result
    }
}