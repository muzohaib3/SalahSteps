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

            tvArabic.visibility = View.GONE
            tvTranslation.visibility = View.GONE
            llActions.visibility = View.GONE

            cardDua.setOnClickListener {
                llActions.visibility = View.VISIBLE
                val isVisible = tvArabic.visibility == View.VISIBLE
                TransitionManager.beginDelayedTransition(cardDua, AutoTransition())

                tvArabic.visibility = if (isVisible) View.GONE else View.VISIBLE
                tvTranslation.visibility = if (isVisible) View.GONE else View.VISIBLE
                llActions.visibility = if (isVisible) View.GONE else View.VISIBLE
            }

            btnCopy.setOnClickListener {
                val clipboard = it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("Dua", dua.arabic + "\n" + dua.translation))
                Toast.makeText(it.context, "Copied!", Toast.LENGTH_SHORT).show()
            }

            btnShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "${dua.title}\n\n${dua.arabic}\n${dua.translation}")
                it.context.startActivity(Intent.createChooser(intent, "Share Dua"))
            }

            btnAudio.setOnClickListener {
//                MediaPlayer().apply {
//                    setDataSource(dua.audioUrl)   // audio URL stored in model
//                    prepare()
//                    start()
//                }
                Toast.makeText(it.context, "No audio available for this Dua", Toast.LENGTH_SHORT).show()
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