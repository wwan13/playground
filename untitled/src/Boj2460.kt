import java.io.BufferedReader

fun String.toIntPair(): Pair<Int, Int> {
    return this.split(" ").let {
        it[0].toInt() to it[1].toInt()
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val passengers = mutableListOf(0)

    // 첫 번쨰 입력
    processWithInputCheck(this, passengers) {
        check(it.first == 0) { "출발역에서 내린 사람은 0명 이여야 합니다." }
    }

    // 2 - 9 번째 입력
    for (i in 2..9) {
        processWithInputCheck(this, passengers)
    }

    // 마지막 입력
    processWithInputCheck(this, passengers) {
        check(it.second == 0) { "종착역에서 탄 사람은 0명 이여야 합니다." }
    }

    val answer = passengers.max()
    println(answer)
}

fun processWithInputCheck(
    reader: BufferedReader,
    passengers: MutableList<Int>,
    inputCheck: (Pair<Int, Int>) -> Unit = { },
) {
    val data = reader.readLine().toIntPair()
    inputCheck.invoke(data)

    val passenger = passengers.last() - data.first + data.second

    check(passenger >= 0) { "승객 수는 음수가 될 수 없습니다." }
    check(passenger <= 10000) { "기차의 정원은 10000명 입니다." }
    passengers.add(passenger)
}