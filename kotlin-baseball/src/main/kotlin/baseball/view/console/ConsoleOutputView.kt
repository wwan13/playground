package baseball.view.console

import baseball.view.OutputView

class ConsoleOutputView : OutputView {

    override fun displayGameStartMessage() {
        println("숫자 야구 게임을 시작합니다.")
    }

    override fun displayCompareResult(
        strikeCount: Int,
        ballCount: Int
    ) {
        when {
            strikeCount != 0 && ballCount == 0 -> println("${strikeCount}스트라이크")
            strikeCount == 0 && ballCount != 0 -> println("${ballCount}볼")
            strikeCount != 0 && ballCount != 0 -> println("${strikeCount}스트라이크 ${ballCount}볼")
            else -> println("낫싱")
        }
    }

    override fun displayGameEndMessage() {
        println("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
    }
}
