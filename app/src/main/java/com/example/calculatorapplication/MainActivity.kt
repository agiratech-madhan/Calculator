package com.example.calculatorapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var textInput: TextView? = null
    private var lastNumber: Boolean = false
    private var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textInput = findViewById(R.id.textInput)

    }

    fun onPressed(view: View) {
        textInput?.append((view as Button).text)
        lastNumber = true
        lastDot = false
    }

    fun onclear(view: View) {
        textInput?.text = ""
    }

    fun onDotClicked(view: View) {
        if (lastNumber && !lastDot) {
            textInput?.append(".")
            lastNumber = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        textInput?.text.let {
            if (lastNumber && !onOperatorAdded(it.toString())) {
                textInput?.append((view as Button).text)
                lastNumber = false
                lastDot = false
            }
        }
    }

    fun onOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("+") || value.contains("-") || value.contains("*")
        }
    }

    private fun removeZero(value: String): String {
        var result = value
        if (result.contains(".0"))
            result = result.substring(0, result.length - 2)
        return result
    }

    fun onEqual(view: View) {
        if (lastNumber) {
            var value = textInput?.text.toString()
            var prefix: String = ""
            try {
                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1)
                }
                if (value.contains("-")) {
                    val splitValur = value.split("-")
                    var first = splitValur[0]
                    var second = splitValur[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    textInput?.text = (first.toDouble() - second.toDouble()).toString()
                } else if (value.contains("+")) {
                    val splitValur = value.split("+")
                    var first = splitValur[0]
                    var second = splitValur[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    textInput?.text = (first.toDouble() + second.toDouble()).toString()
                } else if (value.contains("*")) {
                    val splitValur = value.split("*")
                    var first = splitValur[0]
                    var second = splitValur[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    textInput?.text = (first.toDouble() * second.toDouble()).toString()
                } else if (value.contains("/")) {
                    val splitValur = value.split("/")
                    var first = splitValur[0]
                    var second = splitValur[1]
                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    textInput?.text = removeZero((first.toDouble() / second.toDouble()).toString())
                }


            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }

    }
}


