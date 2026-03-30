package com.example.namaztracker.fragment.namaz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namaztracker.R
import com.example.namaztracker.adapter.NamazInfoAdapter
import com.example.namaztracker.databinding.FragmentNamazInfoBinding
import com.example.namaztracker.databinding.FragmentQuranBinding
import com.example.namaztracker.model.NamazItemModel
import com.example.namaztracker.popFragment

class NamazFragment : Fragment() {

    private var _binding: FragmentNamazInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNamazInfoBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun  initViews(){
        with(binding){

            tvToolbarHeader.text = "Salah"
            binding.btBack.setOnClickListener {
                popFragment(requireFragmentManager())
            }

            rvNamaz.apply {
                adapter = NamazInfoAdapter(NamazItemModel().getNamazTypeList())
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

}