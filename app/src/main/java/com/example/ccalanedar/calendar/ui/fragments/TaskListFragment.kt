package com.example.ccalanedar.calendar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccalanedar.R
import com.example.ccalanedar.calendar.ui.adapters.TaskListAdapter
import com.example.ccalanedar.calendar.ui.viewmodels.CalendarViewModel
import com.example.ccalanedar.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : Fragment() {


    private var _viewModel: CalendarViewModel? = null
    private val viewModel get() = _viewModel!!

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private var listAdapter: TaskListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProvider(requireActivity())[CalendarViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setObservers()
    }


    private fun setObservers() {
        listAdapter = TaskListAdapter()
        binding.listRecyclerView.adapter = listAdapter
        viewModel.getAllTasks().observe(viewLifecycleOwner) {
            listAdapter?.setItems(it)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TaskListFragment()
    }
}