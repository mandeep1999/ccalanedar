package com.example.ccalanedar.calendar.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO
import com.example.ccalanedar.calendar.ui.components.TaskRowComponent

class TaskListAdapter(private val deleteCallback: (Int) -> Unit) :
    RecyclerView.Adapter<TaskListAdapter.TaskRowViewHolder>() {


    companion object {
        private val TASK_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<TaskModelDTO>() {
            override fun areItemsTheSame(oldItem: TaskModelDTO, newItem: TaskModelDTO): Boolean {
                return oldItem.taskId == newItem.taskId
            }

            override fun areContentsTheSame(oldItem: TaskModelDTO, newItem: TaskModelDTO): Boolean {
                return oldItem == newItem
            }

        }
    }

    private val differ = AsyncListDiffer(this, TASK_ITEM_COMPARATOR)

    fun setItems(list: List<TaskModelDTO>) {
        differ.submitList(list)
    }

    class TaskRowViewHolder(root: View) :
        RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskRowViewHolder {
        val itemView = TaskRowComponent(parent.context)
        itemView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return TaskRowViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TaskRowViewHolder, position: Int) {
        val item = differ.currentList[position]
        (holder.itemView as TaskRowComponent).setComponent(item, deleteCallback = deleteCallback)
    }
}