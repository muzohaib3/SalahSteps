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
        with(holder.binding){

            val data= list[position]
            ivIcon.setImageResource(data.drawableItem)
            tvNamazName.text = data.name
            tvTime.text = data.time

            viewModel.fajrSalah = 0
            viewModel.zuhrSalah = 0
            viewModel.asrSalah = 0
            viewModel.maghribSalah = 0
            viewModel.ishaSalah = 0

            when(data.name)
            {
                "Fajr"->{
                    toggleNamaz.setOnCheckedChangeListener { _, isChecked ->
                        viewModel.fajrSalah = if (isChecked) 1 else 0
                    }
                }
                "Zuhr"->{
                    toggleNamaz.setOnCheckedChangeListener { _, isChecked ->
                        viewModel.zuhrSalah = if (isChecked) 1 else 0
                    }
                }
                "Asr"->{
                    toggleNamaz.setOnCheckedChangeListener { _, isChecked ->
                        viewModel.asrSalah = if (isChecked) 1 else 0
                    }
                }
                "Maghrib"->{
                    toggleNamaz.setOnCheckedChangeListener { _, isChecked ->
                        viewModel.maghribSalah = if (isChecked) 1 else 0
                    }
                }
                "Isha"->{
                    toggleNamaz.setOnCheckedChangeListener { _, isChecked ->
                        viewModel.ishaSalah = if (isChecked) 1 else 0
                    }
                }

            }

            logi("NamazMarkingAdapter", "fajr = ${viewModel.fajrSalah}, zuhr =  ${viewModel.zuhrSalah}" +
                    ",asr = ${viewModel.asrSalah}, maghrib= ${viewModel.maghribSalah}, isha = ${viewModel.ishaSalah}")

            if (isDateMatched && sModel != null)
            {
                when(data.name){
                    "Fajr"->{
                        if (sModel.fajr == 1) toggleNamaz.isChecked = true else toggleNamaz.isChecked = false
                    }
                    "Zuhr"->{
                        if (sModel.zuhr == 1) toggleNamaz.isChecked = true else toggleNamaz.isChecked = false
                    }
                    "Asr"->{
                        if (sModel.asr == 1) toggleNamaz.isChecked = true else toggleNamaz.isChecked = false
                    }
                    "Maghrib"->{
                        if (sModel.maghrib == 1) toggleNamaz.isChecked = true else toggleNamaz.isChecked = false
                    }
                    "Isha"->{
                        if (sModel.isha == 1) toggleNamaz.isChecked = true else toggleNamaz.isChecked = false
                    }
                }
            }
            else{
                logi("NamazMarkingAdapter","model is null")
            }

        }
    }

    override fun getItemCount(): Int = list.size
}
