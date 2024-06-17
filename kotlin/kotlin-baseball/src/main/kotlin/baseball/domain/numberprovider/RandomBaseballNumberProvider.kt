package baseball.domain.numberprovider

import baseball.domain.constant.BASEBALL_NUMBER_MAX_VALUE
import baseball.domain.constant.BASEBALL_NUMBER_MIN_VALUE
import baseball.domain.constant.BASEBALL_NUMBER_SIZE
import camp.nextstep.edu.missionutils.Randoms

class RandomBaseballNumberProvider : BaseballNumberProvider {

    override fun provide(): List<Int> {
        val baseballNumbers = mutableSetOf<Int>()

        while (baseballNumbers.size < BASEBALL_NUMBER_SIZE) {
            baseballNumbers
                .add(Randoms.pickNumberInRange(BASEBALL_NUMBER_MIN_VALUE, BASEBALL_NUMBER_MAX_VALUE))
        }
        return baseballNumbers.toList()
    }
}
