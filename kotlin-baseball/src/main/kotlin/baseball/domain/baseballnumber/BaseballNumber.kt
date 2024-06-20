package baseball.domain.baseballnumber

import baseball.constant.BASEBALL_NUMBER_MAX_VALUE
import baseball.constant.BASEBALL_NUMBER_MIN_VALUE

data class BaseballNumber(
    val value: Int,
) {

    init {
        if (value < BASEBALL_NUMBER_MIN_VALUE || value > BASEBALL_NUMBER_MAX_VALUE) {
            throw IllegalArgumentException("숫자 야구 숫자는 1-9 사이의 숫자만 입력해 주세요")
        }
    }
}
