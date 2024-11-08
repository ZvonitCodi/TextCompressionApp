package com.example.algorithm_lz78

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val history: MutableList<Pair<String, String>>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val inputTextView: TextView = itemView.findViewById(R.id.inputTextView)
        val resultTextView: TextView = itemView.findViewById(R.id.resultTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val (input, result) = history[position]
        holder.inputTextView.text = "Входные данные: $input"
        holder.resultTextView.text = "Результат сжатия: $result"
    }

    override fun getItemCount(): Int = history.size

    fun addItem(input: String, result: String) {
        history.add(0, Pair(input, result))
        // Уведомляем адаптер, что в начале списка добавлен элемент
        notifyItemInserted(0)
    }
}
