package com.example.namaztracker.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.namaztracker.R
import com.example.namaztracker.adapter.NamazListAdapter
import com.example.namaztracker.adapter.NamazTrackerFilterAdapter
import com.example.namaztracker.adapter.NamazTrackkerListAdapter
import com.example.namaztracker.architecture.database.AppDB
import com.example.namaztracker.databinding.FragmentTrackkerBinding
import com.example.namaztracker.getClassName
import com.example.namaztracker.loge
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TrackkerFragment : Fragment() {

    private lateinit var binding: FragmentTrackkerBinding
    private val db by lazy { AppDB.getFileDatabase(requireContext()).appDAO() }
    private val handler = Handler(Looper.getMainLooper())
    private var currentPosition = 0
    private lateinit var namazTrackkerAdapter:NamazTrackkerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrackkerBinding.inflate(inflater, container, false )
        initViews()
        return binding.root
    }

    private fun initViews(){

        val images = listOf(R.drawable.carousel1, R.drawable.carousel2, R.drawable.carousel3)
        namazTrackkerAdapter = NamazTrackkerListAdapter(images)
        val sdf = SimpleDateFormat("dd-MMM-yyyy")
        val currentDate = sdf.format(Date())
        val text1 = "Today's Date : "
        val text2 = currentDate

        val spannable = SpannableString("$text1$text2")
        spannable.setSpan(ForegroundColorSpan(Color.BLACK), 0, text1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan(Color.DKGRAY), text1.length, (text1 + text2).length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvMonthName.text = spannable

        populateSalahList(getCurrentWeekDates())

        binding.llRvHeader.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = namazTrackkerAdapter
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.llRvHeader)

        var isWeekendChecked = false
        var isMonthlyChecked = false
        var isYearlyChecked = false

        binding.chipWeekend.checkedIcon = null
        binding.chipMonthly.checkedIcon = null

        binding.chipWeekend.setOnClickListener {
            isWeekendChecked = true
            isMonthlyChecked = false
            isYearlyChecked = false
            if (isWeekendChecked == true){
                changeWeekendChipColor(isWeekendChecked)
                changeMonthlyChipColor(isMonthlyChecked)
                changeYearlyChipColor(isYearlyChecked)
            }
            populateSalahList(getCurrentWeekDates())
        }

        binding.chipMonthly.setOnClickListener {
            isWeekendChecked = false
            isMonthlyChecked = true
            isYearlyChecked = false
            if (isMonthlyChecked == true){
                changeMonthlyChipColor(isMonthlyChecked)
                changeWeekendChipColor(isWeekendChecked)
                changeYearlyChipColor(isYearlyChecked)
            }
            populateSalahList(getMonthDatesOfCurrentMonth())
        }

        binding.chipYearly.setOnClickListener {
            isWeekendChecked = false
            isMonthlyChecked = false
            isYearlyChecked = true
            if (isYearlyChecked == true){
                changeMonthlyChipColor(isMonthlyChecked)
                changeWeekendChipColor(isWeekendChecked)
                changeYearlyChipColor(isYearlyChecked)
            }
            populateSalahList(getDatesOfCurrentYear())
        }

        startAutoScroll()

    }

    fun getMonthDatesOfCurrentMonth(): List<String> {

        return try {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)

            val dates = mutableListOf<String>()
            calendar.set(year, month, 1)

            val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            for (day in 1..maxDay) {
                val dateString = String.format("%02d/%02d/%04d", day, month + 1, year)
                dates.add(dateString)
            }
            return dates
        }catch (e:Exception){
            loge(getClassName(requireContext()),"${e.message}")
            emptyList<String>()
        }

    }

    fun getDatesOfCurrentYear(): List<String> {
        return try {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val dates = mutableListOf<String>()

            // Loop through each month (0 to 11)
            for (month in 0..11) {
                calendar.set(year, month, 1)
                val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                // Loop through each day of that specific month
                for (day in 1..maxDay) {
                    // Formatting as DD/MM/YYYY
                    val dateString = String.format("%02d/%02d/%04d", day, month + 1, year)
                    dates.add(dateString)
                }
            }
            return dates
        } catch (e: Exception) {
            loge(getClassName(requireContext()), "${e.message}")
            emptyList<String>()
        }
    }

    fun getCurrentWeekDates(): List<String> {

        return try {
            val calendar = Calendar.getInstance()

            calendar.firstDayOfWeek = Calendar.MONDAY
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

            val dates = mutableListOf<String>()
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            for (i in 0..6) {
                dates.add(sdf.format(calendar.time))
                calendar.add(Calendar.DAY_OF_YEAR, 1) // Agla din add karein
            }

            return dates
        }
        catch (e:Exception){
            loge(getClassName(requireContext()),"${e.message}")
            emptyList<String>()
        }

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

    private fun populateSalahList(list:List<String>){
        binding.rvNamazList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = NamazListAdapter(/*getDatesOfCurrentMonth()*/list, requireContext(), db)
        }
    }

    private fun changeWeekendChipColor(isChecked:Boolean){
        if (isChecked == true){
            binding.chipWeekend.chipBackgroundColor= ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.dark_gray))
            binding.chipWeekend.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white)))
        }else{
            binding.chipWeekend.chipBackgroundColor= ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.light_grey))
            binding.chipWeekend.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black)))
        }
    }

    private fun changeMonthlyChipColor(isChecked:Boolean){
        if (isChecked == true){
            binding.chipMonthly.chipBackgroundColor= ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.dark_gray))
            binding.chipMonthly.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white)))
        }else{
            binding.chipMonthly.chipBackgroundColor= ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.light_grey))
            binding.chipMonthly.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black)))
        }
    }

    private fun changeYearlyChipColor(isChecked:Boolean){
        if (isChecked == true){
            binding.chipYearly.chipBackgroundColor= ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.dark_gray))
            binding.chipYearly.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white)))
        }else{
            binding.chipYearly.chipBackgroundColor= ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.light_grey))
            binding.chipYearly.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black)))
        }
    }

}