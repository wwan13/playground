fun main() = with(System.`in`.bufferedReader()) {
    val numbers = mutableListOf<Int>()
    for (i in 1..9) {
        val number = readLine().toInt()
        check(number <= 100) { "number는 100보다 클 수 없습니다." }
        numbers.add(number)
    }

    val maxValue = numbers.max()
    val indexAt = numbers.indexOf(maxValue) + 1

    println(maxValue)
    println(indexAt)
}