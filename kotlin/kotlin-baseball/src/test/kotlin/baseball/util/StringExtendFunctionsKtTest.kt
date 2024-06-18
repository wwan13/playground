package baseball.util

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class StringExtendFunctionsKtTest : BehaviorSpec({

    Given("숫자 문자열을 숫자 리스트로 변환하려 할 떄") {

        When("정수로만 이루어진 문자열이 입력되면") {
            val value = "123"
            val actual = value.toIntList()

            Then("숫자 리스트로 변환된다") {
                actual.javaClass shouldBe ArrayList::class.java
                actual[0] shouldBe 1
                actual[1] shouldBe 2
                actual[2] shouldBe 3
            }
        }

        When("문자를 포함한 문자열이 입력되면") {
            val value = "12a"

            Then("숫자 리스트로 변환된다") {
                shouldThrow<IllegalArgumentException> {
                    value.toIntList()
                }
            }
        }
    }
})
