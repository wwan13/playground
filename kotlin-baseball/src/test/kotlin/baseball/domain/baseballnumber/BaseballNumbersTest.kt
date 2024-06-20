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

    Given("특정 인덱스의 BaseballNumber를 가져오려고 할 때") {
        val values = listOf(1, 2, 3)
        val baseballNumbers = BaseballNumbers.of(values)

        When("올바른 인덱스의 값을 입력하면") {
            val index = 2
            val actual = baseballNumbers.get(index)

            Then("정상적으로 값을 가져올 수 있다.") {
                actual.value shouldBe values[index]
            }
        }
    }

    Given("특정 값을 가지는 BaseballNumber가 포함되어 있는지 확인하려 할 때") {
        val givenValues = listOf(1, 2, 3)
        val baseballNumbers = BaseballNumbers.of(givenValues)

        When("값이 존재 한다면") {
            val targetValue = 2
            val baseballNumber = BaseballNumber(targetValue)
            val actual = baseballNumbers.containsAt(baseballNumber)

            Then("위치하는 인덱스를 반환한다") {
                actual shouldBe givenValues.indexOf(targetValue)
            }
        }

        When("값이 존재하지 않는다면") {
            val targetValue = 5
            val baseballNumber = BaseballNumber(targetValue)
            val actual = baseballNumbers.containsAt(baseballNumber)

            Then("-1을 반환한다") {
                actual shouldBe -1
            }
        }
    }

    Given("팩토리를 이용해 BaseballNumbers 객체를 생성하려 할 떄") {

        When("3개의 정수 리스트를 입력하면") {
            val values = listOf(1, 2, 3)
            val actual = BaseballNumbers.of(values)

            Then("BaseballNumber 리스트를 가지는 객체가 생성된다") {
                actual.values.javaClass shouldBe ArrayList::class.java
                actual.values[0].javaClass shouldBe BaseballNumber::class.java
            }
        }
    }
})
