package com.example.namaztracker.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.namaztracker.R
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namaztracker.activity.MoreItemsActivity
import com.example.namaztracker.adapter.IslamicInfoTypeAdapter
import com.example.namaztracker.databinding.FragmentNamazBinding
import com.example.namaztracker.fragment.bottomsheet.GenericBottomSheet
import com.example.namaztracker.fragment.bottomsheet.GenericErrorBottomSheet
import com.example.namaztracker.fragment.bottomsheet.GenericModel
import com.example.namaztracker.getClassName
import com.example.namaztracker.isOnWifi
import com.example.namaztracker.loge
import com.example.namaztracker.model.TaleemModel

class MoreFragment : Fragment() {

    private lateinit var binding:FragmentNamazBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNamazBinding.inflate(inflater, container, false )
        initViews()
        return binding.root
    }

    private fun initViews(){

        val homeItems = listOf(
            TaleemModel(R.drawable.namaz, "Namaz"),
            TaleemModel(R.drawable.masnoon_dua, "Masnoon Duaen"),
            TaleemModel(R.drawable.hadith, "Hadith"),
            TaleemModel(R.drawable.islamic_faqs, "Islamic FAQ"),
            TaleemModel(R.drawable.qibla, "Qibla"),
            TaleemModel(R.drawable.quran, "Quran")
        )

        try {
            binding.rvItems.apply {
                layoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
                adapter = IslamicInfoTypeAdapter(homeItems) { item ->

                    when (item.title) {
                        "Namaz" -> fragmentToActivity("namaz")
                        "Masnoon Duaen" -> fragmentToActivity("masnoon_duas")
                        "Hadith" -> fragmentToActivity("hadith")
                        "Islamic FAQ" -> fragmentToActivity("islamic_faq")
                        "Qibla" -> fragmentToActivity("qibla")
                        "Quran" -> {
                            if(requireContext().isOnWifi()){
                                fragmentToActivity("quran")
                            }else{
                                val errorSheet = GenericErrorBottomSheet("please turn on your internet")
                                errorSheet.show(childFragmentManager, "")
                            }
                        }
                    }
                }
            }
            val resId = R.anim.layout_animation_fall_down
            val animation = AnimationUtils.loadLayoutAnimation(context, resId)
            binding.rvItems.layoutAnimation = animation

        }catch (e:Exception){
            loge(getClassName(requireContext()),"${e.message}")
        }

        val listItems = arrayListOf(
            GenericModel("Day Theme"),
            GenericModel("Night Theme"),
//            GenericModel("Select Language"),
//            GenericModel("Font Size"),
//            GenericModel("Notification Sound")
        )
        binding.ivMenu.setOnClickListener {
            val bottomSheet = GenericBottomSheet.newInstance(listItems){ item->
                when(item.name){
                    "Day Theme"->{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    "Night Theme"->{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                }
                Toast.makeText(requireContext(), "Selected: ${item.name}", Toast.LENGTH_SHORT).show()
            }
            bottomSheet.show(childFragmentManager, "")
        }

    }

    private fun fragmentToActivity(key:String){
        val intent = Intent(requireActivity(), MoreItemsActivity::class.java)
        intent.putExtra("key", key)
        startActivity(intent)
    }



}