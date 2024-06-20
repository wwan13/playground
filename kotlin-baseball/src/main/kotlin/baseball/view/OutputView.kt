package baseball.view

interface OutputView {

    fun displayGameStartMessage()

    fun displayCompareResult(
        strikeCount: Int,
        ballCount: Int,
    )

    fun displayGameEndMessage()
}
