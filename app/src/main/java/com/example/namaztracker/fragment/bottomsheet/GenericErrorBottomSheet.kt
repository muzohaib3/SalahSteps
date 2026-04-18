package com.example.namaztracker.fragment.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.namaztracker.R
import com.example.namaztracker.databinding.FragmentGenericSuccessBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class GenericErrorBottomSheet(val message:String) : BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentGenericSuccessBottomSheetBinding
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentBottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenericSuccessBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.btnOk.setOnClickListener { dismiss() }

        binding.tvMessage.text = message
        dialog?.let {
            val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
            behavior.skipCollapsed = true
            it.setCanceledOnTouchOutside(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}