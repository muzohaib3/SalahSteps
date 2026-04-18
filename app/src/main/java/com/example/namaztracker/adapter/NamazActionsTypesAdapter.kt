package com.example.namaztracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import com.example.namaztracker.databinding.IslamicFqasItemBinding
import com.example.namaztracker.model.IslamicFaqModel

class NamazActionsTypesAdapter (
    private val list: List<IslamicFaqModel>
) : RecyclerView.Adapter<NamazActionsTypesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = IslamicFqasItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.islamic_type_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding){
            tvTitle.text = item.question
            tvAnswer.text = item.answer
        }

    }

    override fun getItemCount() = list.size
}