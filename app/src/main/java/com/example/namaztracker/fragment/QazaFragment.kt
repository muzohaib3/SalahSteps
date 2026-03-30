package com.example.namaztracker.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.namaztracker.R
import com.example.namaztracker.adapter.NamazTrackkerListAdapter
import com.example.namaztracker.adapter.QazaNamazListAdapter
import com.example.namaztracker.architecture.database.AppDB
import com.example.namaztracker.databinding.FragmentQazaBinding
import com.example.namaztracker.databinding.FragmentTrackkerBinding
import com.example.namaztracker.logi

class QazaFragment : Fragment() {

    private lateinit var binding: FragmentQazaBinding
    private val db by lazy { AppDB.getFileDatabase(requireContext()).appDAO() }
    private val handler = Handler(Looper.getMainLooper())
    private var currentPosition = 0
    private lateinit var namazTrackkerAdapter: NamazTrackkerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQazaBinding.inflate(inflater, container, false )
        initViews()
        return binding.root
    }

    private fun initViews(){

        val count = db.getTotalMissedNamazCount()
        binding.tvNamazCount.text= "Total qaza namaz : $count"
        val images = listOf(R.drawable.carousel1, R.drawable.carousel2, R.drawable.carousel3)
        namazTrackkerAdapter = NamazTrackkerListAdapter(images)

        val getMissedNamaz = db.getTotalMissedNamaz()
        binding.rvQazaNamaz.apply {
            adapter = QazaNamazListAdapter(getMissedNamaz, requireContext())
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.llRvHeader.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = namazTrackkerAdapter
        }
        binding.llRvHeader.onFlingListener = null
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.llRvHeader)

        startAutoScroll()
        logi("${this::class.simpleName}","list --> ${getMissedNamaz}")

    }

    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            if (namazTrackkerAdapter.itemCount > 0) {
                currentPosition = (currentPosition + 1) % namazTrackkerAdapter.itemCount
                binding.llRvHeader.smoothScrollToPosition(currentPosition)
                handler.postDelayed(this, 3000)
            }
        }
    }

    private fun startAutoScroll() {
        handler.postDelayed(autoScrollRunnable, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(autoScrollRunnable)
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }

}