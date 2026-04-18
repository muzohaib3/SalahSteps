package com.example.namaztracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import com.example.namaztracker.databinding.ItemNamazStepBinding
import com.example.namaztracker.isVisible

class NamazStepsAdapter(private val list: List<NamazStepModel>) :
    RecyclerView.Adapter<NamazStepsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemNamazStepBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_namaz_step, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step = list[position]
        with(holder.binding){
            tvStepTitle.text = step.title
            tvInstruction.text = step.instruction

            if (step.imageRes == null){
                ivStepImage.isVisible(false)
            }else{
                step.imageRes?.let { ivStepImage.setImageResource(it) }
            }
            if (step.arabicText != null) {
                tvArabic.visibility = View.VISIBLE
                tvArabic.text = step.arabicText
            } else {
                tvArabic.visibility = View.GONE
            }
        }

    }

    override fun getItemCount() = list.size
}

data class NamazStepModel(
    val title: String,
    val instruction: String,
    val arabicText: String?,
    val imageRes: Int?
)