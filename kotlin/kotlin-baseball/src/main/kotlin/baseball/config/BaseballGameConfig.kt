package baseball.config

import baseball.domain.numbercomparator.BaseballNumberComparator
import baseball.domain.numberprovider.BaseballNumberProvider
import baseball.domain.numberprovider.random.RandomBaseballNumberProvider
import baseball.domain.playmanager.BaseballGamePlayManager

class BaseballGameConfig {

    private val baseballNumberProvider: BaseballNumberProvider = RandomBaseballNumberProvider()
    private val baseballNumberComparator: BaseballNumberComparator = BaseballNumberComparator()

    val baseballGamePlayManager: BaseballGamePlayManager = BaseballGamePlayManager(
        this.baseballNumberComparator,
        this.baseballNumberProvider
    )
}
