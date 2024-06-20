package baseball.domain.numberprovider

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe

class RandomBaseballNumberProviderTest : BehaviorSpec({

    Given("인수를 제공하지 않고") {
        val baseballNumberProvider = RandomBaseballNumberProvider()

        When("provider()를 호출하면") {
            val actual = baseballNumberProvider.provide()

            Then("임의의 3자리의 수가 생성된다.") {
                actual.size shouldBe 3
            }

            Then("생성된 수의 각 자리는 1-9 사이의 수 이다.") {
                actual.forEach {
                    it shouldBeInRange IntRange(1, 9)
                }
            }
        }
    }
})
