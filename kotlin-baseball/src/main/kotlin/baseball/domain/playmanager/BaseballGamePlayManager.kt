package baseball.domain.playmanager

import baseball.domain.baseballnumber.BaseballNumbers
import baseball.domain.numbercomparator.BaseballNumberComparator
import baseball.domain.numbercomparator.BaseballNumberCompareResult
import baseball.domain.numberprovider.BaseballNumberProvider

class BaseballGamePlayManager(
    private val baseballNumberComparator: BaseballNumberComparator,
    private val baseballNumberProvider: BaseballNumberProvider,
) {

    fun initNewComputer(): BaseballNumbers {
        val randomNumbers = baseballNumberProvider.provide()
        return BaseballNumbers.of(randomNumbers)
    }

    fun compareNumbers(
        computer: BaseballNumbers,
        player: BaseballNumbers,
    ): BaseballNumberCompareResult {
        return baseballNumberComparator.compare(computer, player)
    }
}
