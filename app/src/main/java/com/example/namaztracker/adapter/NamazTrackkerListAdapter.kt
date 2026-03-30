package com.example.namaztracker.adapter

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import com.example.namaztracker.architecture.database.AppDB
import com.example.namaztracker.architecture.database.AppDao
import com.example.namaztracker.databinding.NamazListItemBinding
import com.example.namaztracker.databinding.TrackkerLayoutItemBinding
import com.example.namaztracker.loge
import com.example.namaztracker.model.NamazModel
import java.util.Locale

class NamazTrackkerListAdapter(private val imageList: List<Int>) : RecyclerView.Adapter<NamazTrackkerListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = TrackkerLayoutItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trackker_layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentImage = imageList[position]

        with(holder.binding) {
            ivImage.setImageResource(currentImage)
        }
    }

    override fun getItemCount(): Int = imageList.size
}

