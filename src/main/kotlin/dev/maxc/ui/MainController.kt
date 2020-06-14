package dev.maxc.ui

import dev.maxc.io.LocalFileReader
import dev.maxc.io.LocalFileWriter
import dev.maxc.io.TIME_SEPARATOR
import dev.maxc.util.TimeConverter
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import java.net.URL
import java.util.*
import kotlin.concurrent.thread
import kotlin.system.exitProcess

/**
 * @author Max Carter
 * @since 06/06/2020
 */
class MainController : Initializable {
    @FXML
    lateinit var timeDisplay: Label

    @FXML
    lateinit var closeLabel: Label

    @FXML
    lateinit var toggleButton: Button

    @Volatile
    private var play = true

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val fileWriter = LocalFileWriter()

        //close button
        closeLabel.setOnMouseClicked {
            fileWriter.writeJobTime(timeDisplay.text)
            exitProcess(0)
        }

        val fileReader = LocalFileReader()
        val time = fileReader.getJobTime()
        timeDisplay.text = TimeConverter.getTextFromTime(time)

        toggleButton.setOnAction {
            play = !play
            if (play) {
                initTimer(fileReader.getJobTime())
            } else {
                fileWriter.writeJobTime(timeDisplay.text)
            }
        }

        //starts timer thread
        initTimer(time)
    }

    /**
     * Initiates the timer at a certain time
     */
    private fun initTimer(time: IntArray = intArrayOf(0, 0, 0)) {
        thread(start = true) {
            while (play) {
                Thread.sleep(1000)
                displayTime(time)
            }
        }
    }

    private fun displayTime(time: IntArray) {
        fun fixLength(item: Int) = (if (item in 0..9) "0" else "") + item

        time[2]++
        for (i in 1 downTo 0) {
            if (time[i] >= 60) {
                time[i + 1]++
                time[i] = 0
            }
        }

        Platform.runLater {
            timeDisplay.text =
                fixLength(time[0]) + TIME_SEPARATOR + fixLength(time[1]) + TIME_SEPARATOR + fixLength(time[2])
        }
    }
}