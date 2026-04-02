package com.example.namaztracker.fragment

import android.icu.util.IslamicCalendar
import android.icu.util.TimeZone
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namaztracker.R
import com.example.namaztracker.adapter.NamazMarkingAdapter
import com.example.namaztracker.architecture.database.AppDB
import com.example.namaztracker.architecture.viewModel.MainViewModel
import com.example.namaztracker.databinding.FragmentNamazDetailBinding
import com.example.namaztracker.loge
import com.example.namaztracker.logi
import com.example.namaztracker.model.NamazItemModel
import com.example.namaztracker.model.NamazModel
import com.example.namaztracker.model.SalahDataModel
import com.example.namaztracker.popFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

class NamazDetailFragment : Fragment() {

    private lateinit var binding: FragmentNamazDetailBinding
    private lateinit var model: NamazModel
    private val db by lazy { AppDB.getFileDatabase(requireContext()).appDAO() }
    var isDateMatched = false
    private lateinit var viewModel:MainViewModel
    private val TAG = this::class.simpleName
    private val isUpdateMode = false
    private val scope = CoroutineScope(Dispatchers.IO)
    private val mainThread = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNamazDetailBinding.inflate(inflater, container, false )
        setUpViewModel()
        returnDayAndDate()
        initViews()
        return binding.root
    }

    private fun initViews(){

        try {
            isUpdateMode()
            val data = returnDayAndDate()
            val day = data.first
            val date = data.second
            binding.tvDate.text = "$day, $date . ${getIslamicDateIcu()}"
            binding.tvToolbarHeader.text = "Mark Your Salah"
            binding.btBack.setOnClickListener { popFragment(requireFragmentManager()) }

            val salahModel = db.getRecordAccDate(date)

            if (salahModel != null)
            {
                if (salahModel.date == date){
                    isDateMatched = true
                }else{
                    isDateMatched = false
                }
                binding.btSave.isEnabled = false
                binding.btSave.setBackgroundResource(R.drawable.grey_squared_circle_bg)
                binding.btSave.alpha = 0.7F
                setAdapter(setSalahList(), isDateMatched, salahModel)
            }
            else{
                binding.btSave.isEnabled = true
                setAdapter(setSalahList(), isDateMatched, null)
            }

        }catch (e:Exception){
            loge("${this::class.simpleName}", "exception --> ${e.message}")
        }

        binding.btSave.setOnClickListener {
            addRecord()
        }
    }

    private fun setAdapter(list:List<NamazItemModel>, isDateMatched:Boolean, salahModel:SalahDataModel?){
        binding.rvNamaz.apply {
            adapter = NamazMarkingAdapter(requireContext(),list, viewModel, isDateMatched, salahModel, false)
            layoutManager = LinearLayoutManager(requireContext())
            val resId = R.anim.layout_animation_fall_down
            val animation = AnimationUtils.loadLayoutAnimation(context, resId)
            layoutAnimation = animation
        }
    }

    private fun setUpViewModel(){
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    fun getIslamicDateIcu(): String {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            val islamicCalendar = IslamicCalendar(TimeZone.getDefault(), Locale.getDefault())
            val day = islamicCalendar.get(IslamicCalendar.DAY_OF_MONTH)
            val month = islamicCalendar.get(IslamicCalendar.MONTH) + 1
            val year = islamicCalendar.get(IslamicCalendar.YEAR)
//            return "$day-$month-$year HIJRI"
            return "$year HIJRI"
        } else {
            return "API level 24+ required for this method."
        }
    }

    fun addRecord(){

        if (viewModel != null){

            Log.i(TAG, "result --> " +
                    "fajrSalah ${viewModel.fajrSalah}" +
                    "zuhrSalah ${viewModel.zuhrSalah} " +
                    "asrSalah ${viewModel.asrSalah} " +
                    "maghribSalah ${viewModel.maghribSalah} " +
                    "ishaSalah ${viewModel.ishaSalah}")

            val salahModel = SalahDataModel(viewModel.fajrSalah, viewModel.zuhrSalah, viewModel.asrSalah, viewModel.maghribSalah, viewModel.ishaSalah, model.date)

            scope.launch {
                db.insertSalah(salahModel)
            }
            mainThread.launch {
                Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show()
            }
//            findNavController().navigate(R.id.action_namazDetail_to_tracker)
            findNavController().popBackStack()

        }

    }

    private fun returnDayAndDate():Pair<String,String>{
        val arg = arguments
        var day = ""
        var date = ""
        if (arg != null){
            model = (arg?.getSerializable("model") as? NamazModel)!!
            day = model.day
            date = model.date
        }
        return Pair(day, date)
    }

    private fun setSalahList():List<NamazItemModel>{
        val list = NamazItemModel().getNamazList()
        return list
    }

    private fun isUpdateMode():Boolean{
        var isUpdateMode = false
        val argument = arguments
        if (argument != null){
             isUpdateMode = argument.getBoolean("isUpdateMode", false)
            logi(TAG!!, "isUpdateMode: $isUpdateMode flag")
        }
        return isUpdateMode
    }





}