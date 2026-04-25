package com.example.namaztracker.fragment.namaz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namaztracker.R
import com.example.namaztracker.adapter.NamazStepModel
import com.example.namaztracker.adapter.NamazStepsAdapter
import com.example.namaztracker.databinding.FragmentNamazGuideBinding
import com.example.namaztracker.logi
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

        runCatching {
            action = arguments?.getString(ARG_ACTION) ?: ""

            when(action){
                "core_salah" -> setAdapter(NamazGuide.namazSteps)
                "wudu_guide" -> setAdapter(NamazGuide.wuduSteps)
                "conditions" -> setAdapter(NamazGuide.namazConditionsList)
                "p_types" -> setAdapter(NamazGuide.prayerTypesList)
                "rakat" -> setAdapter(NamazGuide.prayerTypesDetailedList)
                "duas" -> setAdapter(NamazGuide.namazDuasStepByStep)
            }



            binding.tvToolbarHeader.text = "Salah Guide"
            binding.btBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }.onFailure { it->
            logi(""+this::class.simpleName, "exception: ${it.message}")
        }

        binding.btBack.setOnClickListener {
            requireActivity().onBackPressed()
        }


    }

    private fun setAdapter(list:List<NamazStepModel>){
        binding.rvNamazSteps.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = NamazStepsAdapter(list)
        }
    }



    companion object {
        private const val ARG_ACTION = "action"

        fun newInstance(action: String): NamazGuideFragment {
            return NamazGuideFragment().apply {
                arguments = bundleOf(ARG_ACTION to action)
            }
        }
    }



}