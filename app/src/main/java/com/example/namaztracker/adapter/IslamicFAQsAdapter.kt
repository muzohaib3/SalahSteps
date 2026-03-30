package com.example.namaztracker.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.namaztracker.databinding.IslamicFqasItemBinding
import com.example.namaztracker.databinding.IslamicTypeItemBinding
import com.example.namaztracker.model.IslamicFaqModel
import com.example.namaztracker.model.TaleemModel

class IslamicFAQsAdapter(
    private val list: List<IslamicFaqModel>
) : RecyclerView.Adapter<IslamicFAQsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = IslamicFqasItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.islamic_fqas_item, parent, false)
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