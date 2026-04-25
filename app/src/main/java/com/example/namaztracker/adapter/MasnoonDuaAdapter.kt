package com.example.namaztracker.adapter

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.content.ClipboardManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import com.example.namaztracker.databinding.ItemDuaBinding
import com.example.namaztracker.model.DuaItem

class MasnoonDuaAdapter(
    private val originalList: List<DuaItem>
) : RecyclerView.Adapter<MasnoonDuaAdapter.ViewHolder>() {

    private var filteredList = ArrayList(originalList)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemDuaBinding.bind(view)
        var isExpanded = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dua, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dua = filteredList[position]

        with(holder.binding) {

            tvTitle.text = dua.title
            tvArabic.text = dua.arabic
            tvTranslation.text = dua.translation

            // Default collapsed state
            tvArabic.visibility = View.GONE
            tvTranslation.visibility = View.GONE
            llActions.visibility = View.GONE

            cardDua.setOnClickListener {
                val isCurrentlyVisible = tvArabic.visibility == View.VISIBLE

                // Better Transition for smooth collapse
                val transition = AutoTransition().apply {
                    duration = 200L                    // Fast but smooth
                    interpolator = android.view.animation.AccelerateDecelerateInterpolator()
                }

                TransitionManager.beginDelayedTransition(cardDua.parent as ViewGroup, transition)                // ↑↑↑ Yeh line important hai - cardDua ki jagah cardDua.parent use karo

                if (isCurrentlyVisible) {
                    // Collapse
                    tvArabic.visibility = View.GONE
                    tvTranslation.visibility = View.GONE
                    llActions.visibility = View.GONE
                } else {
                    // Expand
                    tvArabic.visibility = View.VISIBLE
                    tvTranslation.visibility = View.VISIBLE
                    llActions.visibility = View.VISIBLE
                }
            }

            // Copy & Share buttons
            btnCopy.setOnClickListener {
                val clipboard = it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("Dua", "${dua.arabic}\n${dua.translation}"))
                Toast.makeText(it.context, "Copied!", Toast.LENGTH_SHORT).show()
            }

            btnShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "${dua.title}\n\n${dua.arabic}\n${dua.translation}")
                }
                it.context.startActivity(Intent.createChooser(intent, "Share Dua"))
            }
        }
    }

    override fun getItemCount() = filteredList.size

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            ArrayList(originalList)
        } else {
            ArrayList(originalList.filter {
                it.title.contains(query, true) || it.arabic.contains(query)
            })
        }
        notifyDataSetChanged()
    }

}