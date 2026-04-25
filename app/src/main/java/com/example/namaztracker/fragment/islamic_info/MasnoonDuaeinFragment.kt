package com.example.namaztracker.fragment.islamic_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namaztracker.R
import com.example.namaztracker.adapter.MasnoonDuaAdapter
import com.example.namaztracker.databinding.FragmentMasnoonDuaeinBinding
import com.example.namaztracker.model.DuaData

class MasnoonDuaeinFragment : Fragment() {

    private lateinit var binding: FragmentMasnoonDuaeinBinding
    private lateinit var adapter: MasnoonDuaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMasnoonDuaeinBinding.inflate(inflater, container, false)

        initViews()
        setupRecycler()
        setupSearch()

        return binding.root
    }

    private fun initViews(){
        binding.tvToolbarHeader.text = "Masnoon Duaein"

        binding.btBack.setOnClickListener {
            requireActivity().finish()
        }

    }

    private fun setupRecycler() {
        adapter = MasnoonDuaAdapter(DuaData.duaList)
        binding.rvDua.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDua.adapter = adapter


    }

    private fun setupSearch() {
//        binding.etSearch.addTextChangedListener { text ->
//            adapter.filter(text.toString())
//        }
    }
}