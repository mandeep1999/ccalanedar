package com.example.ccalanedar.calendar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ccalanedar.R
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO
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
        listAdapter = TaskListAdapter(::onDelete)
        binding.listRecyclerView.adapter = listAdapter
        viewModel.getAllTasks().observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                showEmptyScreen()
            } else {
                showDataScreen(it)
            }
        }
    }

    private fun showEmptyScreen() {
        binding.emptyTextView.visibility = View.VISIBLE
        binding.listRecyclerView.visibility = View.GONE
    }

    private fun showDataScreen(taskModelDTOS: List<TaskModelDTO>) {
        binding.emptyTextView.visibility = View.GONE
        binding.listRecyclerView.visibility = View.VISIBLE
        listAdapter?.setItems(taskModelDTOS)
    }

    private fun onDelete(id: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.are_you_sure_you_want_to_delete_this))
            .setPositiveButton(
                getString(R.string.yes)
            ) { _, _ -> viewModel.deleteTask(id) }.setNegativeButton(R.string.no) { _, _ ->

            }.create().show()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TaskListFragment()
    }
}