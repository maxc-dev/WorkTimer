package dev.maxc.util

import dev.maxc.io.TIME_SEPARATOR

object TimeConverter {
    fun getTextFromTime(time: IntArray) = "${time[0]}$TIME_SEPARATOR${time[1]}$TIME_SEPARATOR${time[2]}"
}