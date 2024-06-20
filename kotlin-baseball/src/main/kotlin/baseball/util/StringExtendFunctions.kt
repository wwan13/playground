package baseball.util

fun String.toIntList(): List<Int> {
    return this.chunked(1).map {
        try {
            Integer.parseInt(it)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("숫자만 입력할 수 있습니다.")
        }
    }
}
