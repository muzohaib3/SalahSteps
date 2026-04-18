package com.example.namaztracker.adapter

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.namaztracker.R
import com.example.namaztracker.architecture.database.AppDB
import com.example.namaztracker.architecture.database.AppDao
import com.example.namaztracker.databinding.NamazListItemBinding
import com.example.namaztracker.fragment.bottomsheet.GenericErrorBottomSheet
import com.example.namaztracker.loge
import com.example.namaztracker.model.NamazModel
import com.example.namaztracker.showToast
import java.util.Locale

class NamazListAdapter(
    private val list: List<String>,
    private val context: Context,
    private val appDao: AppDao,
) : RecyclerView.Adapter<NamazListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = NamazListItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.namaz_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        with(holder.binding) {

            tvDate.text = data
            tvDay.text = ", ${getDayName(data)}"
            llListItem.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))

            try {
                val salahModel = appDao.getRecordAccDate(date = data)

                when {
                    salahModel == null -> {
                        llListItem.setBackgroundColor(
                            ContextCompat.getColor(context, R.color.white)
                        )
                    }

                    salahModel.fajr == 1 && salahModel.zuhr == 1 &&
                            salahModel.asr == 1 && salahModel.maghrib == 1 &&
                            salahModel.isha == 1 -> {

                        llListItem.setBackgroundColor(
                            ContextCompat.getColor(context, R.color._light_green)
                        )
                    }

                    else -> {
                        llListItem.setBackgroundColor(
                            ContextCompat.getColor(context, R.color.light_pink)
                        )
                    }
                }

                llListItem.setOnClickListener { view ->
                    val model = NamazModel(date = data, day = getDayName(data))
                    val bundle = bundleOf(
                        "model" to model,
                        "isUpdate" to (salahModel != null)
                    )

                    if (salahModel !=  null){
//                        view.findNavController().navigate(R.id.action_tracker_to_namazDetailStatusFragment, bundle)
                        val sheet = GenericErrorBottomSheet("your salah cannot be updated")
                        val activity = context as? FragmentActivity
                        activity?.supportFragmentManager?.let { manager ->
                            sheet.show(manager, "")
                        }
                    }
                    else{
                        view.findNavController().navigate(R.id.action_tracker_to_namazDetail, bundle)
                    }
                }

            } catch (e: Exception) {
                loge(
                    "${context::class.simpleName}",
                    "exception --> ${e.message}"
                )
            }
        }
    }


    fun getDayName(dateStr: String): String {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = sdf.parse(dateStr)

            val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            dayFormat.format(date!!)
        } catch (e: Exception) {
            ""
        }
    }

    override fun getItemCount(): Int = list.size


}
