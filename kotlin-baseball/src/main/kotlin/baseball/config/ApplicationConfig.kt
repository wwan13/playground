package baseball.config

import baseball.view.InputView
import baseball.view.OutputView
import baseball.view.console.ConsoleInputView
import baseball.view.console.ConsoleOutputView

class ApplicationConfig {

    val inputView: InputView = ConsoleInputView()
    val outputView: OutputView = ConsoleOutputView()
}
