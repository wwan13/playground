package baseball.domain.numbercomparator

import baseball.domain.baseballnumber.BaseballNumbers
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BaseballNumberComparatorTest : BehaviorSpec({

    Given("두 개의 BaseballNumbers를 비교하려 할 때") {
        val comparator = BaseballNumberComparator()

        When("자리가 다른 1개의 값이 포함되어 있을 때") {
            val comparison = BaseballNumbers.of(listOf(1, 2, 3))
            val contrast = BaseballNumbers.of(listOf(4, 1, 5))
            val actual = comparator.compare(comparison, contrast)

            Then("0s 1b를 담은 결과 객체가 반환된다") {
                actual.strike shouldBe 0
                actual.ball shouldBe 1
            }
        }

        When("자리가 다른 2개의 값이 포함되어 있을 때") {
            val comparison = BaseballNumbers.of(listOf(1, 2, 3))
            val contrast = BaseballNumbers.of(listOf(4, 1, 2))
            val actual = comparator.compare(comparison, contrast)

            Then("0s 3b를 담은 결과 객체가 반환된다") {
                actual.strike shouldBe 0
                actual.ball shouldBe 2
            }
        }

        When("자리가 다른 3개의 값이 포함되어 있을 때") {
            val comparison = BaseballNumbers.of(listOf(1, 2, 3))
            val contrast = BaseballNumbers.of(listOf(3, 1, 2))
            val actual = comparator.compare(comparison, contrast)

            Then("0s 3b를 담은 결과 객체가 반환된다") {
                actual.strike shouldBe 0
                actual.ball shouldBe 3
            }
        }

        When("자리가 같은 1개의 값이 포함되어 있을 때") {
            val comparison = BaseballNumbers.of(listOf(1, 2, 3))
            val contrast = BaseballNumbers.of(listOf(1, 4, 5))
            val actual = comparator.compare(comparison, contrast)

            Then("1s 0b를 담은 결과 객체가 반환된다") {
                actual.strike shouldBe 1
                actual.ball shouldBe 0
            }
        }

        When("자리가 같은 2개의 값이 포함되어 있을 때") {
            val comparison = BaseballNumbers.of(listOf(1, 2, 3))
            val contrast = BaseballNumbers.of(listOf(1, 2, 5))
            val actual = comparator.compare(comparison, contrast)

            Then("2s 0b를 담은 결과 객체가 반환된다") {
                actual.strike shouldBe 2
                actual.ball shouldBe 0
            }
        }

        When("자리가 같은 1개의 값과 자리가 다른 1개의 값이 포함되어 있을 때") {
            val comparison = BaseballNumbers.of(listOf(1, 2, 3))
            val contrast = BaseballNumbers.of(listOf(1, 4, 2))
            val actual = comparator.compare(comparison, contrast)

            Then("0s 3b를 담은 결과 객체가 반환된다") {
                actual.strike shouldBe 1
                actual.ball shouldBe 1
            }
        }

        When("자리가 같은 1개의 값과 자리가 다른 2개의 값이 포함되어 있을 때") {
            val comparison = BaseballNumbers.of(listOf(1, 2, 3))
            val contrast = BaseballNumbers.of(listOf(1, 3, 2))
            val actual = comparator.compare(comparison, contrast)

            Then("0s 3b를 담은 결과 객체가 반환된다") {
                actual.strike shouldBe 1
                actual.ball shouldBe 2
            }
        }

        When("자리가 같은 2개의 값이 포함되어 있을 때") {
            val comparison = BaseballNumbers.of(listOf(1, 2, 3))
            val contrast = BaseballNumbers.of(listOf(1, 2, 3))
            val actual = comparator.compare(comparison, contrast)

            Then("2s 0b를 담은 결과 객체가 반환된다") {
                actual.strike shouldBe 3
                actual.ball shouldBe 0
            }

            Then("그 객체는 정답임을 알려준단") {
                actual.isAnswer shouldBe true
            }
        }
    }
})