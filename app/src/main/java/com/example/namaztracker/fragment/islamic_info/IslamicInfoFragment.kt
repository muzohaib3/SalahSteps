package com.example.namaztracker.fragment.islamic_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namaztracker.R
import com.example.namaztracker.adapter.IslamicFAQsAdapter
import com.example.namaztracker.adapter.IslamicHadithAdapter
import com.example.namaztracker.databinding.FragmentIslamicInfoBinding
import com.example.namaztracker.getJsonFromRaw
import com.example.namaztracker.model.FaqResponse
import com.example.namaztracker.model.HadithResponse
import com.example.namaztracker.model.IslamicFAQ
import com.example.namaztracker.model.IslamicFaqModel
import com.google.gson.Gson

class IslamicInfoFragment : Fragment() {

    private lateinit var binding: FragmentIslamicInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentIslamicInfoBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews(){

        with(binding)
        {
            val jsonFileString = getJsonFromRaw(requireContext(), R.raw.islamic_faqs)

            if (jsonFileString != null) {
                val response: FaqResponse = Gson().fromJson(jsonFileString, FaqResponse::class.java)
                val islamicKnowledge = response.faqs
                rvIslamicFaqs.apply {
                    adapter = IslamicFAQsAdapter(islamicKnowledge)
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }

            tvToolbarHeader.text = "Islamic Information"
            binding.btBack.setOnClickListener {
                requireActivity().finish()
            }

        }

    }

}