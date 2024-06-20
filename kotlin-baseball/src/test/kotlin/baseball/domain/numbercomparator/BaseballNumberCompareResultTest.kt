package baseball.domain.numbercomparator

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BaseballNumberCompareResultTest : BehaviorSpec({

    Given("비교 결과가 정답인지 확인하려 할 때") {
        val ball = 2

        When("Strike 값이 3이면") {
            val strike = 3
            val result = BaseballNumberCompareResult(strike, ball)
            val actual = result.isAnswer

            Then("true를 반환한다.") {
                actual shouldBe true
            }
        }

        When("Strike 값이 3이 아나면") {
            val strike = 2
            val result = BaseballNumberCompareResult(strike, ball)
            val actual = result.isAnswer

            Then("true를 반환한다.") {
                actual shouldBe false
            }
        }
    }
})
