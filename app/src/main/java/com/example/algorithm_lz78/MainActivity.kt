package com.example.algorithm_lz78

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.PriorityQueue

class MainActivity : AppCompatActivity() {

    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputEditText = findViewById<EditText>(R.id.inputEditText)
        val compressButton = findViewById<Button>(R.id.compressButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val algorithmRadioGroup = findViewById<RadioGroup>(R.id.algorithmRadioGroup)
        val historyRecyclerView = findViewById<RecyclerView>(R.id.historyRecyclerView)
        val clearButton = findViewById<Button>(R.id.clearButton)

        historyAdapter = HistoryAdapter(mutableListOf())
        historyRecyclerView.layoutManager = LinearLayoutManager(this)
        historyRecyclerView.adapter = historyAdapter

        compressButton.setOnClickListener {
            val inputText = inputEditText.text.toString()
            if (inputText.isNotEmpty()) {
                val selectedAlgorithmId = algorithmRadioGroup.checkedRadioButtonId
                val compressedText = when (selectedAlgorithmId) {
                    R.id.radioButtonLZ78 -> lz78Compress(inputText)
                    R.id.radioButtonHuffman -> huffmanEncode(inputText)
                    R.id.radioButtonEntropy -> arithmeticEncode(inputText)
                    else -> ""
                }
                resultTextView.text = compressedText

                historyAdapter.addItem(inputText, compressedText)
            }
        }
        clearButton.setOnClickListener {
            inputEditText.text.clear()
            resultTextView.text = ""
        }
    }
}
