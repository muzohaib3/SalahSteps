package com.example.namaztracker.fragment.bottomsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.databinding.GenericBottomsheetListItemBinding

class GenericBottomSheetAdapter<T>(
    private val onItemClick: (T) -> Unit
) : RecyclerView.Adapter<GenericBottomSheetAdapter<T>.GenericViewHolder>() {

    private val items = ArrayList<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val binding = GenericBottomsheetListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class GenericViewHolder(private val binding: GenericBottomsheetListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            if (item is GenericModel) {
                binding.tvNetworkName.text = item.name
            }

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    fun submitList(newList: List<T>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}

data class GenericModel(
    var name:String
)