package com.example.namaztracker.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.namaztracker.databinding.IslamicFqasItemBinding
import com.example.namaztracker.databinding.IslamicInfoDetailsItemBinding
import com.example.namaztracker.databinding.IslamicTypeItemBinding
import com.example.namaztracker.model.IslamicFaqModel
import com.example.namaztracker.model.NamazDetailModel
import com.example.namaztracker.model.TaleemModel

class NamazInfoAdapter(
    private val list: List<NamazDetailModel>
) : RecyclerView.Adapter<NamazInfoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = IslamicInfoDetailsItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.islamic_info_details_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding){
           tvDetails.text = item.details
           tvName.text = item.name
           tvRakat.text = item.rakat
           tvTiming.text = item.timing
        }

    }

    override fun getItemCount() = list.size
}