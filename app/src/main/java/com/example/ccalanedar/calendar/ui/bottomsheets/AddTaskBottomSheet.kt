package com.example.ccalanedar.calendar.ui.bottomsheets

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ccalanedar.R
import com.example.ccalanedar.calendar.core.utils.Utility
import com.example.ccalanedar.calendar.ui.viewmodels.CalendarViewModel
import com.example.ccalanedar.databinding.BottomSheetAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddTaskBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetAddTaskBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: CalendarViewModel? = null
    private val viewModel get() = _viewModel!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            R.style.BottomDialogStyle
        )
        _viewModel = ViewModelProvider(requireActivity())[CalendarViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_add_task, container, true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initViews()
        initListeners()
    }

    private fun initViews() {
        viewModel.newTaskTitle?.let { binding.titleEditText.setText(it) }
        viewModel.newTaskDescription?.let { binding.descriptionEditText.setText(it) }
        viewModel.newTaskDate = arguments?.getLong(DATE_LONG)

        binding.dateEditText.setText(viewModel.newTaskDate?.let {
            Utility.convertTimestampToReadableDate(
                it
            )
        })
    }

    private fun initListeners() {
        binding.titleEditText.doOnTextChanged { text, start, before, count ->
            viewModel.newTaskTitle = text.toString()
            binding.titleTil.error = null
        }
        binding.descriptionEditText.doOnTextChanged { text, start, before, count ->
            viewModel.newTaskDescription = text.toString()
            binding.descriptionTil.error = null
        }

        binding.submitBtn.setOnClickListener {
            if (checkValidation()) {
                viewModel.createNewTask()
                dismiss()
            }
        }
    }

    private fun checkValidation(): Boolean {
        if (Utility.isValidString(viewModel.newTaskTitle).not()) {
            binding.titleTil.error = getString(R.string.please_write_something)
            return false
        }
        if (Utility.isValidString(viewModel.newTaskDescription).not()) {
            binding.descriptionTil.error = getString(R.string.please_write_something)
            return false
        }

        if (viewModel.newTaskDate == null) {
            binding.dateTil.error = getString(R.string.please_write_something)
            return false
        }

        return true
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.clearNewTask()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewModel = null
    }

    companion object {

        const val TAG = "ADD_TASK"

        private const val DATE_LONG: String = "date_long"

        @JvmStatic
        fun getInstance(date: Long): AddTaskBottomSheet {
            return AddTaskBottomSheet().apply {
                arguments = Bundle().apply {
                    putLong(DATE_LONG, date)
                }
            }
        }
    }
}