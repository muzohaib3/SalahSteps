package com.example.namaztracker.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.namaztracker.databinding.IslamicTypeItemBinding
import com.example.namaztracker.model.TaleemModel

class IslamicInfoTypeAdapter(
    private val list: List<TaleemModel>,
    private val onClick: (TaleemModel) -> Unit
) : RecyclerView.Adapter<IslamicInfoTypeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = IslamicTypeItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.islamic_type_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        with(holder.binding) {
            ivIcon.setImageResource(item.icon)
            tvTitle.text = item.title

            root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun getItemCount() = list.size
}