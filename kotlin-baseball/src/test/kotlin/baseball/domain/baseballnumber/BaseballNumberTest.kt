package baseball.domain.baseballnumber

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BaseballNumberTest : BehaviorSpec({

    Given("BaseballNumber 객체를 생성하려 할 때") {

        When("1-9 사이의 숫자가 입력되면") {
            val value = 7
            val actual = BaseballNumber(value)

            Then("정상적으로 생성된다.") {
                actual.value shouldBe value
            }
        }

        When("1보다 작은 수가 입력되면") {
            val value = -1

            Then("예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    BaseballNumber(value)
                }
            }
        }

        When("9보다 큰 수가 입력되면") {
            val value = 10

            Then("예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    BaseballNumber(value)
                }
            }
        }
    }

    Given("두 BaseballNumber 객체를 비교하려 할 때") {

        When("두 객체의 value가 같으면") {
            val value = 3
            val baseballNumber1 = BaseballNumber(value)
            val baseballNUmber2 = BaseballNumber(value)

            val actual = baseballNumber1 == baseballNUmber2

            Then("True를 반환한다") {
                actual shouldBe true
            }
        }

        When("두 객체의 value가 다르면") {
            val value1 = 3
            val value2 = 4
            val baseballNumber1 = BaseballNumber(value1)
            val baseballNUmber2 = BaseballNumber(value2)

            val actual = baseballNumber1 == baseballNUmber2

            Then("False를 반환한다") {
                actual shouldBe false
            }
        }
    }
})
