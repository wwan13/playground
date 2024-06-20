package baseball.domain.status

import baseball.constant.GAME_STATUS_END
import baseball.constant.GAME_STATUS_RESTART

data class BaseballGameStatus(
    val value: Int
) {

    init {
        if (!isValidValue(value)) {
            throw IllegalArgumentException("1혹은 2만 입력할 수 있습니다.")
        }
    }

    private fun isValidValue(value: Int) = value == GAME_STATUS_RESTART || value ==GAME_STATUS_END

    val isRestart: Boolean
        get() {
            return value == GAME_STATUS_RESTART
        }
}
