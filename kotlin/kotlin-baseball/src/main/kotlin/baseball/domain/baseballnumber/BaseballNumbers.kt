package baseball.domain.baseballnumber

import baseball.domain.constant.BASEBALL_NUMBER_SIZE

data class BaseballNumbers(
    val values: List<BaseballNumber>
) {

    init {
        if (isInValidSize(values)) {
            throw IllegalArgumentException("숫자 야구의 숫자는 3자리로 구성되어야 합니다.")
        }
        if (isDuplicated(values)) {
            throw IllegalArgumentException("숫자 야구의 숫자는 중복 되어선 안됩니다.")
        }
    }

    private fun isInValidSize(values: List<BaseballNumber>) = values.size != BASEBALL_NUMBER_SIZE

    private fun isDuplicated(values: List<BaseballNumber>) = values.toSet().size != values.size

    fun get(index: Int) = values[index]

    fun containsAt(value: BaseballNumber) = values.indexOf(value)

    companion object {
        fun of(values: List<Int>): BaseballNumbers {
            val baseballNumbers = values.map {
                BaseballNumber(it)
            }
            return BaseballNumbers(baseballNumbers)
        }
    }
}
