package com.example.ccalanedar.calendar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ccalanedar.R
import com.example.ccalanedar.calendar.ui.adapters.CalendarPagerAdapter
import com.example.ccalanedar.calendar.ui.bottomsheets.AddTaskBottomSheet
import com.example.ccalanedar.calendar.ui.viewmodels.CalendarViewModel
import com.example.ccalanedar.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: CalendarViewModel? = null
    private val viewModel get() = _viewModel!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProvider(requireActivity())[CalendarViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_calendar, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initViewPager()
    }

    private fun initViewPager() {
        viewModel.getAllTasks().observe(viewLifecycleOwner){
            val adapter = CalendarPagerAdapter(requireActivity(), it, ::onDateSelection)
            binding.viewPager.adapter = adapter
            binding.viewPager.setCurrentItem(Int.MAX_VALUE / 2, false)
        }
    }

    private fun onDateSelection(date: Long) {
        val addBottomSheet = AddTaskBottomSheet.getInstance(date)
        childFragmentManager.beginTransaction().add(addBottomSheet, AddTaskBottomSheet.TAG)
            .commitAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewModel = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CalendarFragment()
    }
}