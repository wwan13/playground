package baseball.domain.status

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BaseballGameStatusTest : BehaviorSpec({

    Given("숫자 야구 게임 재시작 여부를 물을 때") {

        When("1을 입력하면") {
            val value = 1
            val actual = BaseballGameStatus(value).isRestart

            Then("재시작 이다") {
                actual shouldBe true
            }
        }

        When("2를 입력하면") {
            val value = 2
            val actual = BaseballGameStatus(value).isRestart

            Then("재시작이 아니다") {
                actual shouldBe false
            }
        }

        When("1과 2가 아닌 값을 입력하면") {
            val value = 3

            Then("예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    BaseballGameStatus(value)
                }
            }
        }
    }
})
