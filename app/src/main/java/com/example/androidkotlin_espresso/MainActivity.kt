package com.example.androidkotlin_espresso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var displayText: TextView
    lateinit var editText: EditText
    lateinit var changeTextButton: Button
    lateinit var openActivityButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayText = findViewById(R.id.textToBeChanged)
        editText = findViewById(R.id.updateNewText)
        changeTextButton = findViewById(R.id.changeTextBt)
        openActivityButton = findViewById(R.id.activityChangeTextBtn)

        // Update the displayed text by the new text entered
        changeTextButton.setOnClickListener {
            val text = editText.text.toString()
            if (text == "") {
                Toast.makeText(this, "Require to fill the message!", Toast.LENGTH_SHORT).show()
            } else {
                displayText.setText(text)
            }
        }

        // Open a new Activity and show the entered text
        openActivityButton.setOnClickListener {
            val intent = Intent(applicationContext, ShowTextActivity::class.java)
            intent.putExtra("text", editText.text.toString())
            startActivity(intent)
        }
    }
}