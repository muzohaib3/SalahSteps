package com.example.namaztracker.fragment.namaz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namaztracker.R
import com.example.namaztracker.adapter.IslamicInfoTypeAdapter
import com.example.namaztracker.adapter.NamazInfoAdapter
import com.example.namaztracker.databinding.FragmentNamazInfoBinding
import com.example.namaztracker.databinding.FragmentQuranBinding
import com.example.namaztracker.getClassName
import com.example.namaztracker.loge
import com.example.namaztracker.logi
import com.example.namaztracker.model.NamazItemModel
import com.example.namaztracker.model.TaleemModel
import com.example.namaztracker.popFragment

class NamazFragment : Fragment() {

    private var _binding: FragmentNamazInfoBinding? = null
    private val binding get() = _binding!!
    private var action = ""

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
                findNavController().navigateUp()
            }

//            rvNamaz.apply {
//                adapter = NamazInfoAdapter(NamazItemModel().getNamazTypeList())
//                layoutManager = LinearLayoutManager(requireContext())
//            }

            try {
                val namazItems = listOf(
                    TaleemModel(R.drawable.namaz, "Core Steps"),
                    TaleemModel(R.drawable.tnc, "Conditions"), // Shariat-e-Namaz
                    TaleemModel(R.drawable.water, "Wudu Guide"),
                    TaleemModel(R.drawable.counter, "Rakat Table"),
                    TaleemModel(R.drawable.shape, "Prayer Types"), // Fard, Sunnat, Nafl
                    TaleemModel(R.drawable.remove, "Mistakes & Sahw"),
                    TaleemModel(R.drawable.masnoon_dua, "Namaz Duas")
                )

                binding.rvItems.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
                    adapter = IslamicInfoTypeAdapter(namazItems) { item ->

                        val action = when (item.title) {
//                            "Conditions" -> findNavController().navigate(R.id.action_namaz_to_masnoonDuaeinFragment)
//                            "Rakat Table" -> findNavController().navigate(R.id.action_namaz_to_islamicInfoFragment)
//                            "Prayer Types" -> findNavController().navigate(R.id.qiblaFinderFragment)
//                            "Namaz Duas" -> findNavController().navigate(R.id.action_namaz_to_quranFragment)

                            "Core Steps" ->  {
                                 "core_salah"
                            }
                            "Wudu Guide" ->{
                                "wudu_guide"
                            }
                            else->{""}
                        }
                        if (action.isNotEmpty() ){
                            var bundle = bundleOf("action" to action)
                            findNavController().navigate(R.id.namazGuideFragment, bundle)
                            logi("NFragment","action --> $action")
                        }

                    }
                }
                val resId = R.anim.layout_animation_fall_down
                val animation = AnimationUtils.loadLayoutAnimation(context, resId)
                binding.rvItems.layoutAnimation = animation

            }catch (e:Exception){
                loge(getClassName(requireContext()),"${e.message}")
            }

        }
    }

}