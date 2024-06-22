package com.example.ccalanedar.core.utils

import android.util.Log
import com.example.ccalanedar.calendar.core.utils.Utility
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilityTest {

    @Test
    fun `empty string returns false`() {
        val result = Utility.isValidString("")
        assertThat(result).isFalse()
    }

    @Test
    fun `null string returns false`() {
        val result = Utility.isValidString(null)
        assertThat(result).isFalse()
    }

    @Test
    fun `just white spaces string returns false`() {
        val result = Utility.isValidString("     ")
        assertThat(result).isFalse()
    }

    @Test
    fun `string with non white spaces returns true`() {
        val result = Utility.isValidString("   s  ")
        assertThat(result).isTrue()
    }

    @Test
    fun `valid epoch time gives correct string`() {
        val result = Utility.convertTimestampToReadableDate(1719057898000)
        assertThat(result).isEqualTo("22/06/2024 17:34:58")
    }

    @Test
    fun `same day timestamp returns true` (){
        val result = Utility.checkSameDay(1719057898000, 1719057899000)
        assertThat(result).isTrue()
    }

    @Test
    fun `different day timestamp returns true` (){
        val result = Utility.checkSameDay(1719057898000, 1711057899000)
        assertThat(result).isFalse()
    }
}