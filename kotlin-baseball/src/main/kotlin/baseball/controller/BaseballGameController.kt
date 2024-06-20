package baseball.controller

import baseball.config.ApplicationConfig
import baseball.config.BaseballGameConfig
import baseball.domain.baseballnumber.BaseballNumbers
import baseball.domain.playmanager.BaseballGamePlayManager
import baseball.domain.status.BaseballGameStatus
import baseball.util.toIntList
import baseball.view.InputView
import baseball.view.OutputView

class BaseballGameController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val gamePlayManager: BaseballGamePlayManager,
) {

    fun playGame() {
        outputView.displayGameStartMessage()
        do {
            playInternal()
            val flag = inputView.inputRestartOrEnd().toInt()
        } while (BaseballGameStatus(flag).isRestart)
    }

    private fun playInternal() {
        val computerNumber = gamePlayManager.initNewComputer()

        do {
            val playerInput = inputView.inputPlayerNumber().toIntList()
            val playerNumber = BaseballNumbers.of(playerInput)

            val result = gamePlayManager.compareNumbers(computerNumber, playerNumber)
            outputView.displayCompareResult(result.strike, result.ball)
        } while (!result.isAnswer)

        outputView.displayGameEndMessage()
    }

    constructor(
        applicationConfig: ApplicationConfig,
        baseballGameConfig: BaseballGameConfig,
    ) : this(
        applicationConfig.inputView,
        applicationConfig.outputView,
        baseballGameConfig.baseballGamePlayManager
    )
}
