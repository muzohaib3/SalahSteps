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
import com.example.namaztracker.databinding.QazaNamazListItemBinding
import com.example.namaztracker.loge
import com.example.namaztracker.model.NamazModel
import com.example.namaztracker.model.SalahDataModel
import java.util.Locale

class QazaNamazListAdapter(
    private val list: List<SalahDataModel>,
    private val context: Context
) : RecyclerView.Adapter<QazaNamazListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = QazaNamazListItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qaza_namaz_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data= list[position]
        with(holder.binding){
            try {
                tvDate.text = data.date
                if (data != null && data.fajr == 1 && data.zuhr == 1 && data.asr == 1 && data.maghrib == 1 && data.isha == 1){

                }else{
                    if (data == null && data.fajr == 0 && data.zuhr == 0 && data.asr == 0 && data.maghrib == 0 && data.isha == 0){
                        if(data.fajr == 0){
                            namaz1.setBackgroundResource(R.drawable.red_squared_circle_bg)
                        }else{
                            namaz1.setBackgroundResource(R.drawable.green_squared_circle_bg)
                        }
                        if(data.zuhr == 0){
                            namaz2.setBackgroundResource(R.drawable.red_squared_circle_bg)
                        }else{
                            namaz2.setBackgroundResource(R.drawable.green_squared_circle_bg)
                        }
                        if(data.asr == 0) {
                            namaz3.setBackgroundResource(R.drawable.red_squared_circle_bg)
                        } else {
                            namaz3.setBackgroundResource(R.drawable.green_squared_circle_bg)
                        }
                        if(data.maghrib == 0){
                            namaz4.setBackgroundResource(R.drawable.red_squared_circle_bg)
                        } else {
                            namaz4.setBackgroundResource(R.drawable.green_squared_circle_bg)
                        }
                        if(data.isha == 0){
                            namaz5.setBackgroundResource(R.drawable.red_squared_circle_bg)
                        }
                        else {
                            namaz5.setBackgroundResource(R.drawable.green_squared_circle_bg)
                        }
                    }
                    else{

                        if(data.fajr == 0)
                            namaz1.setBackgroundResource(R.drawable.red_squared_circle_bg)
                        else
                            namaz1.setBackgroundResource(R.drawable.green_squared_circle_bg)

                        if(data.zuhr == 0)
                            namaz2.setBackgroundResource(R.drawable.red_squared_circle_bg)
                        else
                            namaz2.setBackgroundResource(R.drawable.green_squared_circle_bg)

                        if(data.asr == 0)
                            namaz3.setBackgroundResource(R.drawable.red_squared_circle_bg)
                        else
                            namaz3.setBackgroundResource(R.drawable.green_squared_circle_bg)

                        if(data.maghrib == 0)
                            namaz4.setBackgroundResource(R.drawable.red_squared_circle_bg)
                        else
                            namaz4.setBackgroundResource(R.drawable.green_squared_circle_bg)

                        if(data.isha == 0)
                            namaz5.setBackgroundResource(R.drawable.red_squared_circle_bg)
                        else
                            namaz5.setBackgroundResource(R.drawable.green_squared_circle_bg)
                    }
                }
            }
            catch (e:Exception){
                loge("${context::class.simpleName}","exception --> ${e.message}")
            }
        }
    }

    override fun getItemCount(): Int = list.size


}

