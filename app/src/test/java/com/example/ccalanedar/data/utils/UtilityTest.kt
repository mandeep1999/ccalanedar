package com.example.ccalanedar.data.utils

import com.example.ccalanedar.calendar.data.utility.Utility

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilityTest {

    @Test
    fun `empty date returns false`() {
        val result = Utility.isValidTask(title = "Sample", description = "Sample Desc", date = null)
        assertThat(result).isFalse()
    }

    @Test
    fun `empty title return false`() {
        val result = Utility.isValidTask(title = "", description = "Sample Desc", date = 3242323232)
        assertThat(result).isFalse()
    }

    @Test
    fun `empty description return false`() {
        val result = Utility.isValidTask(title = "Sample", description = "", date = 3242323232)
        assertThat(result).isFalse()
    }

    @Test
    fun `filled title description date returns true`() {
        val result =
            Utility.isValidTask(title = "Sample", description = "Sample Desc", date = 3242323232)
        assertThat(result).isTrue()
    }

    @Test
    fun `empty taskId returns false`() {
        val result = Utility.isValidTaskId(taskId = null)
        assertThat(result).isFalse()
    }

    @Test
    fun `filled taskId returns true`() {
        val result = Utility.isValidTaskId(taskId = 222)
        assertThat(result).isTrue()
    }

}