package baseball.domain.numbercomparator

import baseball.domain.constant.ANSWER_STRIKE_VALUE

data class BaseballNumberCompareResult(
    val strike: Int,
    val ball: Int,
) {

    val isAnswer: Boolean
        get() {
            return strike == ANSWER_STRIKE_VALUE
        }
}
