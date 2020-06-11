package dev.maxc.ui

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

    private var time = intArrayOf(0, 0, 0)

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        closeLabel.setOnMouseClicked {
            exitProcess(0)
        }

        thread(start = true) {
            while (play) {
                Thread.sleep(1)
                displayTime()
            }

        }
    }

    private fun displayTime() {
        fun fixLength(item: Int) = (if (item in 0..9) "0" else "") + item

        time[0]++
        for (i in 0..1) {
            if (time[i] >= 60) {
                time[i+1]++
                time[i] = 0
            }
        }

        Platform.runLater {
            timeDisplay.text = fixLength(time[2]) + ":" + fixLength(time[1]) + ":" +fixLength(time[0])
        }
    }
}