package dev.maxc

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.StageStyle


/**
 * @author Max Carter
 * @since 06/06/2020
 */
class App : Application() {
    override fun start(stage: Stage?) {
        val rootScene = Scene(FXMLLoader.load(javaClass.getResource("/root.fxml")))
        rootScene.stylesheets?.add(javaClass.getResource("/style/fontstyle.css").toExternalForm())
        with (stage!!) {
            scene = rootScene
            x = 80.0
            y = 0.0
            isAlwaysOnTop = true
            initStyle(StageStyle.UNDECORATED)
            show()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(App::class.java)
        }
    }
}