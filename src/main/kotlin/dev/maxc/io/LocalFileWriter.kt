package dev.maxc.io

import java.io.FileWriter
import java.io.PrintWriter
import javax.swing.filechooser.FileSystemView


/**
 * @author Max Carter
 * @since 13/06/2020
 */
class LocalFileWriter {
    fun writeJobTime(time: String) {
        try {
            val writeObject = FileWriter(
                FileSystemView.getFileSystemView().defaultDirectory
                    .path + JOB_STORE_FILE, false
            )
            val printObject = PrintWriter(writeObject)
            printObject.print(time)
            printObject.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}