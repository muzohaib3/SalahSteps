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
import com.example.namaztracker.navigateTo
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

    private fun initViews() {
        with(binding) {

            tvToolbarHeader.text = "Salah"

            btBack.setOnClickListener {
                requireActivity().finish()           // MoreItemsActivity finish
            }

            val namazItems = listOf(
                TaleemModel(R.drawable.namaz, "Core Steps"),
                TaleemModel(R.drawable.tnc, "Conditions"),
                TaleemModel(R.drawable.water, "Wudu Guide"),
                TaleemModel(R.drawable.countdown, "Rakat Table"),
                TaleemModel(R.drawable.shape, "Prayer Types"),
                TaleemModel(R.drawable.masnoon_dua, "Namaz Duas")
            )

            rvItems.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = IslamicInfoTypeAdapter(namazItems) { selectedItem ->

                    val targetFragment: Fragment = when (selectedItem.title) {
                        "Core Steps"    -> NamazGuideFragment.newInstance("core_salah")
                        "Conditions"    -> NamazGuideFragment.newInstance("conditions")
                        "Wudu Guide"    -> NamazGuideFragment.newInstance("wudu_guide")
                        "Rakat Table"   -> NamazGuideFragment.newInstance("rakat")
                        "Prayer Types"  -> NamazGuideFragment.newInstance("p_types")
                        "Namaz Duas"    -> NamazGuideFragment.newInstance("duas")
                        else            -> NamazGuideFragment.newInstance("core_salah")
                    }

                    // ← Yeh line use karo (Extension Function)
                    navigateTo(
                        containerId = R.id.fragmentContainer,     // ← Yeh change karo
                        fragment = targetFragment,
                        addToBackStack = true
                    )

                    logi("NFragment", "Opening: ${selectedItem.title}")
                }
            }

            // Layout Animation
            val resId = R.anim.layout_animation_fall_down
            val animation = AnimationUtils.loadLayoutAnimation(context, resId)
            rvItems.layoutAnimation = animation
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}