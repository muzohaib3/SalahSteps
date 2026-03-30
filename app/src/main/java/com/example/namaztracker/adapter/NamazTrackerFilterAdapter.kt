package com.example.namaztracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import com.example.namaztracker.databinding.NamazTrackkerFilterItemBinding

class NamazTrackerFilterAdapter (
    val list:List<String>,
    val context:Context
) : RecyclerView.Adapter<NamazTrackerFilterAdapter.ViewHolder>() {

    var isItemClicked = false

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = NamazTrackkerFilterItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.namaz_trackker_filter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = list[position]
        with(holder.binding){
            tvFrequency.text = list

            llItem.setOnClickListener {

                when(position){
                    0->{
                        Toast.makeText(context, "weekend clicked", Toast.LENGTH_SHORT).show()
                    }
                    1->{
                        Toast.makeText(context, "monthly clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }

    override fun getItemCount() = list.size
}