package baseball

import baseball.config.ApplicationConfig
import baseball.config.BaseballGameConfig
import baseball.controller.BaseballGameController

fun main() {
    val applicationConfig = ApplicationConfig()
    val baseballGameConfig = BaseballGameConfig()

    val baseballGameController = BaseballGameController(applicationConfig, baseballGameConfig)

    baseballGameController.playGame()
}
