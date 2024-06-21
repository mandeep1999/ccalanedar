package com.example.ccalanedar.calendar.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ccalanedar.R
import com.example.ccalanedar.calendar.ui.fragments.CalendarFragment
import com.example.ccalanedar.calendar.ui.fragments.TaskListFragment
import com.example.ccalanedar.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        showListFragment()
        initBottomBar()
    }

    private fun initBottomBar() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_tasks -> showListFragment()
                R.id.navigation_create -> showCalendarFragment()
            }
            true
        }
    }

    private fun showListFragment() {
        supportFragmentManager.beginTransaction().let {
            it.replace(R.id.container, TaskListFragment.newInstance())
            it.commitAllowingStateLoss()
        }
    }

    private fun showCalendarFragment() {
        supportFragmentManager.beginTransaction().let {
            it.replace(R.id.container, CalendarFragment.newInstance())
            it.commitAllowingStateLoss()
        }
    }

}