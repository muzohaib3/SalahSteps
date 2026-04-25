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
import com.example.namaztracker.fragment.bottomsheet.GenericErrorBottomSheet
import com.example.namaztracker.getClassName
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
    private val scope = CoroutineScope(Dispatchers.IO)
    private val mainThread = CoroutineScope(Dispatchers.Main)
    private var isUpdateMode:Boolean? = false

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

            isUpdateMode = arguments?.getBoolean("isUpdate") ?: false
            isUpdateOrSaveMode()
//            onClickItem(binding.root)

            val data = returnDayAndDate()
            val day = data.first
            val date = data.second
            binding.tvDate.text = "$day, $date . ${getIslamicDateIcu()}"
            binding.tvToolbarHeader.text = "Mark Your Salah"
            binding.btBack.setOnClickListener { popFragment(requireFragmentManager()) }

            val salahModel = db.getRecordAccDate(date)

            if (salahModel != null) {
                if (salahModel.date == date){ isDateMatched = true }
                else{ isDateMatched = false }
                setAdapter(setSalahList(), isDateMatched, salahModel)
            }
            else{
                binding.btSave.isEnabled = true
                setAdapter(setSalahList(), isDateMatched, null)

            }

            logi(getClassName(requireContext()), "salahModel == null\n setSalahList() --> ${setSalahList()}")

            binding.btSave.setOnClickListener{
//                if (isUpdateMode!!){
//                    updateRecord(viewModel.fajrSalah, viewModel.zuhrSalah, viewModel.asrSalah, viewModel.maghribSalah, viewModel.ishaSalah, salahModel?.id!!)
//                }else{
//
//                }
                val vm = viewModel

                val areAllEmpty = !vm.isFChk && !vm.isZChk && !vm.isAChk && !vm.isMChk && !vm.isIChk

                if (areAllEmpty){
                    val sheet = GenericErrorBottomSheet("Please mark your prayer first")
                    sheet.show(parentFragmentManager, "")
                }else{
                    addRecord()
                }

            }

        }catch (e:Exception){
            loge("${this::class.simpleName}", "exception --> ${e.message}")
        }
    }

    private fun setAdapter(list:List<NamazItemModel>, isDateMatched:Boolean, salahModel:SalahDataModel?){
        binding.rvNamaz.apply {
            adapter = NamazMarkingAdapter(requireContext(),list, viewModel, isDateMatched, salahModel)
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

            Log.i(TAG, "result --> addRecord " +
                    "fajrSalah ${viewModel.fajrSalah} zuhrSalah ${viewModel.zuhrSalah} asrSalah ${viewModel.asrSalah}  maghribSalah ${viewModel.maghribSalah}  ishaSalah ${viewModel.ishaSalah}")

            val salahModel = SalahDataModel(viewModel.fajrSalah, viewModel.zuhrSalah,
                viewModel.asrSalah, viewModel.maghribSalah, viewModel.ishaSalah, model.date)

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

    private fun isUpdateOrSaveMode(){

        if (isUpdateMode!!) {
            binding.tvToolbarHeader.text = "Update Your Salah"
            binding.btSave.text = "Update"
        } else {
            binding.tvToolbarHeader.text = "Mark New Salah"
            binding.btSave.text = "Save"
        }
    }

    private fun updateRecord(fajr:Int, zuhr:Int, asr:Int, maghrib:Int, isha:Int, id:Int){
        logi(getClassName(requireContext()),"updateRecord --> fajr: $fajr , zuhr: $zuhr, asr: $asr, maghrib: $maghrib, isha:$isha")
        viewModel.updateSalah(fajr, zuhr, asr, maghrib, isha, id)
        findNavController().popBackStack()
    }





}