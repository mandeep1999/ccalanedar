package com.example.ccalanedar.calendar.ui.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.ccalanedar.R
import com.example.ccalanedar.calendar.core.custom_views.BaseConstraintLayout
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

    init {
        binding.nextMonthButton.setOnClickListener {
            currentCalendar.add(Calendar.MONTH, -1)
            updateCalendar()
        }

        binding.prevMonthButton.setOnClickListener {
            currentCalendar.add(Calendar.MONTH, 1)
            updateCalendar()
        }

        updateCalendar()
    }

    fun setCalendar(calendar: Calendar, dateSelectionListener: (Long) -> Unit) {
        this.currentCalendar = calendar.clone() as Calendar
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
        val dayView = getDateTextView(day.toString(), Color.parseColor("#BDBDBD"))
        binding.calendarGrid.addView(dayView)
    }

    private fun addDayView(day: Int) {
        val dayView = getDateTextView(day.toString())
        dayView.setOnClickListener {
            val selectedDate = currentCalendar.clone() as Calendar
            selectedDate.set(Calendar.DAY_OF_MONTH, day)
            dateSelectionListener?.invoke(selectedDate.timeInMillis)
        }
        binding.calendarGrid.addView(dayView)
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