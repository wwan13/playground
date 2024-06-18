package baseball.domain.playmanager

import baseball.domain.baseballnumber.BaseballNumbers
import baseball.domain.numbercomparator.BaseballNumberComparator
import baseball.domain.numberprovider.RandomBaseballNumberProvider
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe

class BaseballGamePlayManagerTest : BehaviorSpec({

    Given("숫자 야구 게임을 진행하려 할 떄") {
        val baseballGamePlayManager = BaseballGamePlayManager(
            BaseballNumberComparator(),
            RandomBaseballNumberProvider()
        )

        When("컴퓨터 숫자를 초기화 하려 하면") {
            val actual = baseballGamePlayManager.initNewComputer()

            Then("1-9 사이의 무작위 3개의 수로 초기화 해 준다") {
                actual.values.size shouldBe 3
                actual.values.forEach {
                    it.value shouldBeInRange IntRange(1, 9)
                }
            }
        }

        When("컴퓨터의 숫자와 사용자가 입력한 숫자를 비교하려 하면") {
            val computerNumber = BaseballNumbers.of(listOf(1, 2, 3))
            val playerNumber = BaseballNumbers.of(listOf(2, 4, 3))

            val actual = baseballGamePlayManager.compareNumbers(computerNumber, playerNumber)

            Then("결과를 담은 객체를 반환해 준다") {
                actual.strike shouldBe 1
                actual.ball shouldBe 1
            }
        }
    }
})
