package com.example.namaztracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import com.example.namaztracker.architecture.database.AppDB
import com.example.namaztracker.architecture.viewModel.MainViewModel
import com.example.namaztracker.databinding.NamazDetailItemBinding
import com.example.namaztracker.logi
import com.example.namaztracker.model.NamazItemModel
import com.example.namaztracker.model.SalahDataModel

class NamazMarkingAdapter(
    val context: Context,
    val list: List<NamazItemModel>,
    private val viewModel: MainViewModel,
    val isDateMatched:Boolean,
    val sModel:SalahDataModel?,
    val isUpdateMode:Boolean? = false
) : RecyclerView.Adapter<NamazMarkingAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = NamazDetailItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.namaz_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {

            val data = list[position]

            // ---- set UI
            ivIcon.setImageResource(data.drawableItem)
            tvNamazName.text = data.name
            tvTime.text = data.time

            // ---- remove listener before setting checked state
            toggleNamaz.setOnCheckedChangeListener(null)

            if (isDateMatched && sModel != null) {
                when (data.name) {
                    "Fajr" -> {
                        toggleNamaz.isChecked = sModel.fajr == 1
                        viewModel.fajrSalah = sModel.fajr
                    }
                    "Zuhr" -> {
                        toggleNamaz.isChecked = sModel.zuhr == 1
                        viewModel.zuhrSalah = sModel.zuhr
                    }
                    "Asr" -> {
                        toggleNamaz.isChecked = sModel.asr == 1
                        viewModel.asrSalah = sModel.asr
                    }
                    "Maghrib" -> {
                        toggleNamaz.isChecked = sModel.maghrib == 1
                        viewModel.maghribSalah = sModel.maghrib
                    }
                    "Isha" -> {
                        toggleNamaz.isChecked = sModel.isha == 1
                        viewModel.ishaSalah = sModel.isha
                    }
                }
            }

            // ---- Now add listener back
            toggleNamaz.setOnCheckedChangeListener { _, isChecked ->
                when (data.name) {
                    "Fajr" -> viewModel.fajrSalah = if (isChecked) 1 else 0
                    "Zuhr" -> viewModel.zuhrSalah = if (isChecked) 1 else 0
                    "Asr" -> viewModel.asrSalah = if (isChecked) 1 else 0
                    "Maghrib" -> viewModel.maghribSalah = if (isChecked) 1 else 0
                    "Isha" -> viewModel.ishaSalah = if (isChecked) 1 else 0
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size
}