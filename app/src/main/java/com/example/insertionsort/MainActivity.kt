package com.example.insertionsort

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to UI components
        val inputNumbers = findViewById<EditText>(R.id.inputNumbers)
        val sortButton = findViewById<Button>(R.id.sortButton)
        val sortedResult = findViewById<TextView>(R.id.sortedResult)

        // Set up button click listener
        sortButton.setOnClickListener {
            val input = inputNumbers.text.toString()

            // Parse the input into a list of integers
            val numberList = input.split(",")
                .map { it.trim().toIntOrNull() }
                .filterNotNull()

            // Validate input size
            if (numberList.size < 3 || numberList.size > 8) {
                sortedResult.text = "Error: Input size must be between 3 and 8 numbers."
                return@setOnClickListener
            }

            // Clear any previous error message
            sortedResult.text = ""

            // Perform Insertion Sort with intermediate steps
            val intermediateSteps = mutableListOf<String>()
            val sortedList = insertionSort(numberList, intermediateSteps)

            // Display intermediate steps and the sorted result
            sortedResult.text = "Intermediate steps:\n" +
                    intermediateSteps.joinToString("\n") + "\n\nSorted Result:\n" +
                    sortedList.joinToString(", ")
        }
    }

    // Insertion Sort algorithm with intermediate steps
    private fun insertionSort(numbers: List<Int>, steps: MutableList<String>): List<Int> {
        val array = numbers.toMutableList() // Create a mutable copy
        for (i in 1 until array.size) {
            val key = array[i]
            var j = i - 1

            // Move elements that are greater than key one position ahead
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j]
                j--
            }
            array[j + 1] = key

            // Record the state of the array after each iteration
            steps.add(array.joinToString(", "))
        }
        return array
    }
}
