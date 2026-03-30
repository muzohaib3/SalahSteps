package com.example.namaztracker.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.namaztracker.databinding.HadithItemBinding
import com.example.namaztracker.databinding.IslamicFqasItemBinding
import com.example.namaztracker.databinding.IslamicTypeItemBinding
import com.example.namaztracker.model.HadithModel
import android.widget.TextView

class IslamicHadithAdapter(
    private val list: List<HadithModel>
) : RecyclerView.Adapter<IslamicHadithAdapter.ViewHolder>() {

    private var filteredList = ArrayList(list)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = HadithItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hadith_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding){
            tvArabic.text = item.arabic
            tvDescription.text = item.hadith
            tvReference.text = item.reference
        }

    }

    override fun getItemCount() = list.size


    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            ArrayList(list)
        } else {
            ArrayList(list.filter { item ->
                item.hadith.contains(query, ignoreCase = true) ||
                        item.arabic.contains(query, ignoreCase = true) ||
                        item.reference.contains(query, ignoreCase = true)
            })
        }
        notifyDataSetChanged()
    }

}