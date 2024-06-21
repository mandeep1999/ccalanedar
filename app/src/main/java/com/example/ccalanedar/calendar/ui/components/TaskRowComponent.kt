package com.example.ccalanedar.calendar.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.ccalanedar.R
import com.example.ccalanedar.calendar.core.custom_views.BaseConstraintLayout
import com.example.ccalanedar.calendar.core.utils.Utility
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO
import com.example.ccalanedar.databinding.TaskRowItemBinding

class TaskRowComponent : BaseConstraintLayout {

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle,
    )

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    constructor(context: Context) : super(context, null, 0)

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val binding =
        DataBindingUtil.inflate<TaskRowItemBinding>(inflater, R.layout.task_row_item, this, true)

    fun setComponent(taskModelDTO: TaskModelDTO, deleteCallback: (Int) -> Unit) {
        with(taskModelDTO) {
            setTitleText(title)
            setDescriptionText(description)
            setDate(date)
            setClickListener(deleteCallback, taskId)
        }
    }

    private fun setTitleText(title: String?) {
        binding.titleTextView.isVisible = Utility.isValidString(title)
        binding.titleTextView.text = title
    }

    private fun setDescriptionText(description: String?) {
        binding.descriptionTextView.isVisible = Utility.isValidString(description)
        binding.descriptionTextView.text = description
    }

    private fun setDate(date: Long?) {
        binding.dateTextView.isVisible = date != null
        date?.let {
            binding.dateTextView.text = Utility.convertTimestampToReadableDate(it)
        }
    }

    private fun setClickListener(deleteCallback: (Int) -> Unit, taskId: Int) {
        binding.deleteIcon.setOnClickListener {
            deleteCallback.invoke(taskId)
        }
    }
}