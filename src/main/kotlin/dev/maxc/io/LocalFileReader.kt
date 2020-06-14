package dev.maxc.io

import java.io.BufferedReader
import java.io.FileReader
import javax.swing.filechooser.FileSystemView


/**
 * @author Max Carter
 * @since 13/06/2020
 */
class LocalFileReader {
    /**
     * Gets the time of the stored job
     */
    fun getJobTime(): IntArray {
        try {
            val reader = BufferedReader(
                FileReader(
                    FileSystemView.getFileSystemView().defaultDirectory
                        .path + JOB_STORE_FILE
                )
            )
            var inputLine: String?
            while (reader.readLine().also { inputLine = it } != null) {
                val times = inputLine!!.split(TIME_SEPARATOR)
                return intArrayOf(times[0].toInt(), times[1].toInt(), times[2].toInt())
            }
        } catch (ex: Exception) {
            return intArrayOf(0, 0, 0)
        }
        return intArrayOf(0, 0, 0)
    }
}