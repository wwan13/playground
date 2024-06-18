package baseball.view.console

import baseball.view.InputView
import camp.nextstep.edu.missionutils.Console

class ConsoleInputView : InputView {

    override fun inputPlayerNumber(): String {
        print("숫자를 입력해주세요 : ")
        return Console.readLine()
    }

    override fun inputRestartOrEnd(): String {
        println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
        return Console.readLine()
    }
}
