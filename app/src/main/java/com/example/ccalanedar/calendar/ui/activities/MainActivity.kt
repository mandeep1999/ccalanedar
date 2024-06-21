package com.example.ccalanedar.calendar.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ccalanedar.R
import com.example.ccalanedar.calendar.ui.fragments.CalendarFragment
import com.example.ccalanedar.calendar.ui.fragments.TaskListFragment
import com.example.ccalanedar.calendar.ui.viewmodels.CalendarViewModel
import com.example.ccalanedar.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: CalendarViewModel? = null
    private val viewModel get() = _viewModel!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProvider(this)[CalendarViewModel::class.java]
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        showListFragment()
        initBottomBar()
        initLoader()
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

    private fun initLoader() {
        viewModel.showDataLoader.observe(this) {
            binding.loader.isVisible = it
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