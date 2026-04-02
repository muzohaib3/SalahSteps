package com.example.namaztracker.fragment.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namaztracker.R
import com.example.namaztracker.databinding.FragmentGenericBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class GenericBottomSheet : BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentGenericBottomSheetBinding
    private val binding get() = _binding

    private var onItemSelected: ((GenericModel) -> Unit)? = null
    private var networkList: ArrayList<GenericModel> = arrayListOf()

    companion object {
        fun newInstance(
            list: ArrayList<GenericModel>,
            onItemSelected: (GenericModel) -> Unit
        ): GenericBottomSheet = GenericBottomSheet().apply {
            this.networkList = list
            this.onItemSelected = onItemSelected
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentBottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenericBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupRecyclerView()
    }

    private fun setupUI() {
        binding.ivClose.setOnClickListener { dismiss() }

        dialog?.let {
            val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
            behavior.skipCollapsed = true
            it.setCanceledOnTouchOutside(false)
        }
    }

    private fun setupRecyclerView() {
        val genericAdapter = GenericBottomSheetAdapter<GenericModel> { selected ->
            onItemSelected?.invoke(selected)
            dismiss()
        }

        binding.rvNetwork.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = genericAdapter
        }

        genericAdapter.submitList(networkList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}