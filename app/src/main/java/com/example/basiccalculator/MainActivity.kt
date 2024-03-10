package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.widget.Toast
import com.example.basiccalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            if (areFieldsFilled(binding)) {
                doOperation(binding, Operation.Add)
                hideKeyboard()
            } else {
                showWarning()
            }
        }

        binding.subtractButton.setOnClickListener {
            if (areFieldsFilled(binding)) {
                doOperation(binding, Operation.Subtract)
                hideKeyboard()
            } else {
                showWarning()
            }
        }

        binding.multiplyButton.setOnClickListener {
            if (areFieldsFilled(binding)) {
                doOperation(binding, Operation.Multiply)
                hideKeyboard()
            } else {
                showWarning()
            }
        }

        binding.divideButton.setOnClickListener {
            if (areFieldsFilled(binding)) {
                doOperation(binding, Operation.Divide)
                hideKeyboard()
            } else {
                showWarning()
            }
        }

        binding.cleanButton.setOnClickListener {
            clearFields(binding)
        }
    }

    private fun areFieldsFilled(binding: ActivityMainBinding): Boolean {
        return !binding.firstNumber.text.isNullOrBlank() && !binding.secondNumber.text.isNullOrBlank()
    }

    private fun showWarning() {
        Toast.makeText(this,"Preencha ambos os campos antes de realizar a operação.", Toast.LENGTH_SHORT).show()
    }

    private fun doOperation(binding: ActivityMainBinding, operation: Operation) {
        val firstValue = binding.firstNumber.text.toString().toDouble() ?: 0.0
        val secondValue = binding.secondNumber.text.toString().toDouble() ?: 0.0
        var result = 0.0

        when(operation) {
            Operation.Add -> result = firstValue + secondValue
            Operation.Subtract -> result = firstValue - secondValue
            Operation.Multiply -> result = firstValue * secondValue
            Operation.Divide -> {
                if (secondValue != 0.0) {
                    result = firstValue / secondValue
                } else {
                    binding.defaultResultTextView.text = "Erro, divisãp por 0"
                }
            }
        }

        binding.defaultResultTextView.text = String.format("%.2f", result)

    }

    private fun clearFields(binding: ActivityMainBinding) {
        binding.firstNumber.text.clear()
        binding.secondNumber.text.clear()
        binding.defaultResultTextView.text = "0"
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    enum class Operation {
        Add, Subtract, Multiply, Divide
    }
}