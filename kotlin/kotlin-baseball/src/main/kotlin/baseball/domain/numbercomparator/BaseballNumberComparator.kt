package baseball.domain.numbercomparator

import baseball.domain.baseballnumber.BaseballNumbers
import baseball.constant.BASEBALL_NUMBER_SIZE

class BaseballNumberComparator {

    fun compare(
        comparison: BaseballNumbers,
        contrast: BaseballNumbers,
    ): BaseballNumberCompareResult {
        val strikeCount = countStrike(comparison, contrast)
        val ballCount = countBall(comparison, contrast)

        return BaseballNumberCompareResult(strikeCount, ballCount)
    }

    private fun countStrike(
        comparison: BaseballNumbers,
        contrast: BaseballNumbers,
    ): Int {
        val strikeCount = countByCondition { index ->
            val target = contrast.get(index)
            comparison.containsAt(target) == index
        }

        return strikeCount
    }

    private fun countBall(
        comparison: BaseballNumbers,
        contrast: BaseballNumbers,
    ): Int {
        val ballCount = countByCondition { index ->
            val target = contrast.get(index)
            val containedIndex = comparison.containsAt(target)
            containedIndex >= 0 && containedIndex != index
        }

        return ballCount
    }

    private fun countByCondition(
        condition: (Int) -> Boolean,
    ): Int {
        var count = 0
        for (i in 0 until BASEBALL_NUMBER_SIZE) {
            if (condition(i)) count += 1
        }
        return count
    }
}
