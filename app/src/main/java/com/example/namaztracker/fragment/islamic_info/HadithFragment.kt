package com.example.namaztracker.fragment.islamic_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namaztracker.R
import com.example.namaztracker.adapter.IslamicHadithAdapter
import com.example.namaztracker.databinding.FragmentHadithBinding
import com.example.namaztracker.getJsonFromRaw
import com.example.namaztracker.model.Hadith
import com.example.namaztracker.model.HadithResponse
import com.example.namaztracker.popFragment
import com.example.namaztracker.readRawJson
import com.example.namaztracker.toModel
import com.google.gson.Gson


class HadithFragment : Fragment() {

    private lateinit var binding:FragmentHadithBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHadithBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews(){

        with(binding){

            val jsonString = requireContext().readRawJson(R.raw.hadith_data)
            val data = jsonString?.toModel(HadithResponse::class.java)?.hadiths

            if (data != null) {
                rvHadith.apply {
                    adapter = IslamicHadithAdapter(data)
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }

            tvToolbarHeader.text = "Hadith"
            btBack.setOnClickListener { popFragment(requireFragmentManager()) }
            binding.etIslamicHadith.addTextChangedListener { IslamicHadithAdapter(Hadith.hadithList).filter(it.toString()) }

        }
    }


}