package com.example.ccalanedar.calendar.ui.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.ccalanedar.R
import com.example.ccalanedar.calendar.core.custom_views.BaseConstraintLayout
import com.example.ccalanedar.calendar.core.utils.Utility
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO
import com.example.ccalanedar.databinding.CalendarLayoutBinding
import java.util.Calendar

class CalendarComponent : BaseConstraintLayout {


    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle,
    )

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    constructor(context: Context) : super(context, null, 0)

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val binding = DataBindingUtil.inflate<CalendarLayoutBinding>(
        inflater, R.layout.calendar_layout, this, true
    )

    private var currentCalendar: Calendar = Calendar.getInstance()
    private var dateSelectionListener: ((Long) -> Unit)? = null
    private var tasks: List<TaskModelDTO>? = null

    init {
        binding.nextMonthButton.setOnClickListener {
            currentCalendar.add(Calendar.MONTH, 1)
            updateCalendar()
        }

        binding.prevMonthButton.setOnClickListener {
            currentCalendar.add(Calendar.MONTH, -1)
            updateCalendar()
        }

        updateCalendar()
    }

    fun setCalendar(
        calendar: Calendar,
        tasks: List<TaskModelDTO>?,
        dateSelectionListener: (Long) -> Unit
    ) {
        this.currentCalendar = calendar.clone() as Calendar
        this.tasks = tasks
        this.dateSelectionListener = dateSelectionListener
        updateCalendar()
    }

    private fun updateCalendar() {
        binding.monthYearText.text =
            android.text.format.DateFormat.format("MMMM yyyy", currentCalendar)

        binding.calendarGrid.removeAllViews()
        val calendar = currentCalendar.clone() as Calendar
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1


        val prevMonthCalendar = currentCalendar.clone() as Calendar
        prevMonthCalendar.add(Calendar.MONTH, -1)
        val daysInPrevMonth = prevMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)


        for (i in daysInPrevMonth - firstDayOfWeek + 1..daysInPrevMonth) {
            addBlurredDayView(i)
        }


        val daysInMonth = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)


        for (i in 1..daysInMonth) {
            addDayView(i)
        }

        val nextMonthCalendar = currentCalendar.clone() as Calendar
        nextMonthCalendar.add(Calendar.MONTH, 1)


        val daysToShow = 42
        val daysAfterCurrentMonth = daysToShow - (firstDayOfWeek + daysInMonth)
        for (i in 1..daysAfterCurrentMonth) {
            addBlurredDayView(i)
        }
    }

    private fun addBlurredDayView(day: Int) {
        val dayView = getDateTextView(day.toString(), ContextCompat.getColor(context, R.color.colorSecondary))
        binding.calendarGrid.addView(dayView)
    }

    private fun addDayView(day: Int) {
        val dayView = getDateTextView(day.toString(), ContextCompat.getColor(context, R.color.colorPrimary))
        highLightTask(
            dayView,
            (currentCalendar.clone() as Calendar).apply { set(Calendar.DAY_OF_MONTH, day) })
        highLightToday(dayView, day)
        dayView.setOnClickListener {
            val selectedDate = currentCalendar.clone() as Calendar
            selectedDate.set(Calendar.DAY_OF_MONTH, day)
            dateSelectionListener?.invoke(selectedDate.timeInMillis)
        }
        binding.calendarGrid.addView(dayView)
    }

    private fun highLightTask(textView: TextView, calendar: Calendar) {
        var numberOfTasks = 0
        tasks?.map {
            it.date?.let { timestamp ->
                if (Utility.checkSameDay(timestamp, calendar.timeInMillis + 10000)) {
                    numberOfTasks++
                }
            }
        }
        if (numberOfTasks > 0) {
            val text: String = textView.text.toString() + "($numberOfTasks)"
            textView.text = text
            textView.setTextColor(ContextCompat.getColor(context, R.color.lightGrey))
            highLightTextView(textView, R.color.colorPrimary)
        }
    }

    private fun highLightToday(textView: TextView, day: Int) {
        val todayCalendar = Calendar.getInstance()
        if (currentCalendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR) &&
            currentCalendar.get(Calendar.MONTH) == todayCalendar.get(Calendar.MONTH) &&
            day == todayCalendar.get(Calendar.DAY_OF_MONTH)
        ) {
            textView.setTextColor(ContextCompat.getColor(context, R.color.lightGrey))
            highLightTextView(
                textView = textView,
                R.color.accentColor
            )
        }
    }

    private fun highLightTextView(textView: TextView, color: Int) {
        textView.setBackgroundColor(ContextCompat.getColor(context, color))
    }

    private fun getDateTextView(text: String, textColor: Int? = null): TextView {
        val dayView = TextView(context)
        val params = GridLayout.LayoutParams()
        params.width = 0
        params.height = GridLayout.LayoutParams.WRAP_CONTENT
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
        params.setMargins(8, 8, 8, 20)
        dayView.gravity = android.view.Gravity.CENTER
        dayView.layoutParams = params

        dayView.text = text
        textColor?.let { dayView.setTextColor(it) }
        return dayView
    }
}