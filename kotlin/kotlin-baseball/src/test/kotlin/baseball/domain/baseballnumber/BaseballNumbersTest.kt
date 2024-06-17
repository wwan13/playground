package baseball.domain.baseballnumber

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BaseballNumbersTest : BehaviorSpec({

    Given("BaseballNumbers 객체를 생성하려 할 떄") {

        When("중복되지 않은 3개의 숫자가 입력되면") {
            val values = listOf(
                BaseballNumber(1),
                BaseballNumber(2),
                BaseballNumber(3),
            )

            val actual = BaseballNumbers(values)

            Then("정상적으로 생성된다") {
                actual.values shouldBe values
            }

            Then("입력된 수의 순서가 보장된다.") {
                values.forEachIndexed { index, baseballNumber ->
                    baseballNumber shouldBe actual.values[index]
                }
            }
        }

        When("3개보다 적은 숫자가 입력되면") {
            val values = listOf(
                BaseballNumber(1),
                BaseballNumber(2),
            )

            Then("예외가 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    BaseballNumbers(values)
                }
            }
        }

        When("3개보다 많은 숫자가 입력되면") {
            val values = listOf(
                BaseballNumber(1),
                BaseballNumber(2),
                BaseballNumber(3),
                BaseballNumber(4),
            )

            Then("예외가 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    BaseballNumbers(values)
                }
            }
        }

        When("입력된 숫자중 중복이 포함되어 있으면") {
            val values = listOf(
                BaseballNumber(1),
                BaseballNumber(1),
                BaseballNumber(3),
            )

            Then("예외가 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    BaseballNumbers(values)
                }
            }
        }
    }
})
