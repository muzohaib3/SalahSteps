package com.example.namaztracker.fragment.namaz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namaztracker.R
import com.example.namaztracker.adapter.NamazStepModel
import com.example.namaztracker.adapter.NamazStepsAdapter
import com.example.namaztracker.databinding.FragmentNamazGuideBinding
import com.example.namaztracker.model.NamazGuide
import com.example.namaztracker.model.NamazModel

class NamazGuideFragment : Fragment() {

    private lateinit var binding:FragmentNamazGuideBinding
    private var action = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNamazGuideBinding.inflate(inflater, container, false)
        setAdapter()
        return binding.root
    }

    private fun setAdapter(){

        action = arguments?.getString("action").toString()

        when(action){
            "core_salah" -> setAdapter(NamazGuide.namazSteps)
            "wudu_guide" -> setAdapter(NamazGuide.wuduSteps)
        }



        binding.tvToolbarHeader.text = "Salah Guide"
        binding.btBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setAdapter(list:List<NamazStepModel>){
        binding.rvNamazSteps.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = NamazStepsAdapter(list)
        }
    }

}