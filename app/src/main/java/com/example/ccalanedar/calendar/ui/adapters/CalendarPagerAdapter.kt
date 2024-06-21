package com.example.ccalanedar.calendar.ui.adapters

// CalendarPagerAdapter.kt
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccalanedar.calendar.ui.components.CalendarComponent
import java.util.Calendar

class CalendarPagerAdapter(
    private val context: Context,
    private val dateSelectionCallBack: ((Long) -> Unit)
) :
    RecyclerView.Adapter<CalendarPagerAdapter.CalendarViewHolder>() {
    private val currentCalendar: Calendar = Calendar.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val calendarView = CalendarComponent(context)
        calendarView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return CalendarViewHolder(calendarView)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val calendar = currentCalendar.clone() as Calendar
        calendar.add(Calendar.MONTH, position - Int.MAX_VALUE / 2)
        holder.calendarView.setCalendar(calendar, dateSelectionCallBack)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    class CalendarViewHolder(val calendarView: CalendarComponent) :
        RecyclerView.ViewHolder(calendarView)
}
