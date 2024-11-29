package com.example.mysecondapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Finding views by their IDs
        val num1EditText: EditText = findViewById(R.id.et_num1)
        val num2EditText: EditText = findViewById(R.id.et_num2)
        val resultTextView: TextView = findViewById(R.id.tv_result)
        val addButton: Button = findViewById(R.id.btn_add)
        val subtractButton: Button = findViewById(R.id.btn_sub)
        val multiplyButton: Button = findViewById(R.id.btn_mul)
        val divideButton: Button = findViewById(R.id.btn_div)
        val resetButton: Button = findViewById(R.id.btn_reset)

        // Function to calculate and display the result
        fun calculate(operation: String) {
            val num1Text = num1EditText.text.toString()
            val num2Text = num2EditText.text.toString()

            // Check if inputs are not empty
            if (num1Text.isNotEmpty() && num2Text.isNotEmpty()) {
                try {
                    val num1 = num1Text.toDouble()
                    val num2 = num2Text.toDouble()
                    val result = when (operation) {
                        "+" -> num1 + num2
                        "-" -> num1 - num2
                        "*" -> num1 * num2
                        "/" -> {
                            if (num2 != 0.0) num1 / num2 else {
                                Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                                return
                            }
                        }
                        else -> 0.0
                    }
                    resultTextView.text = "Result: $result"
                } catch (e: NumberFormatException) {
                    // Handle invalid number input
                    Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Show a warning if any field is empty
                Toast.makeText(this, "Both fields are required", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up click listeners for each button
        addButton.setOnClickListener { calculate("+") }
        subtractButton.setOnClickListener { calculate("-") }
        multiplyButton.setOnClickListener { calculate("*") }
        divideButton.setOnClickListener { calculate("/") }

        // Reset button click listener
        resetButton.setOnClickListener {
            num1EditText.text.clear()
            num2EditText.text.clear()
            resultTextView.text = "Result:"
        }
    }
}
