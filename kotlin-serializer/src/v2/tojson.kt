package v2

fun Map<String, Any?>.toJson(): String {
    return buildString {
        this@toJson.entries.joinTo(
            this,
            separator = ",",
            prefix = "{",
            postfix = "}"
        ) { (key, value) ->
            "\"${writeString(key)}\":${writeValue(value)}"
        }
    }
}

private fun writeValue(value: Any?): String {
    return when (value) {
        null -> "null"
        is Boolean -> value.toString()
        is Number -> value.toString()
        is String -> "\"${writeString(value)}\""
        is Map<*, *> -> (value as Map<String, Any>).toJson()
        is List<*> -> writeListValue(value as List<Any?>)
        else -> throw IllegalArgumentException("Unsupported type")
    }
}

private fun writeListValue(list: List<Any?>): String {
    return buildString {
        list.joinTo(
            this,
            separator = ",",
            prefix = "[",
            postfix = "]"
        ) { writeValue(it) }
    }
}

private fun writeString(value: String): String {
    return buildString {
        value.forEach { char ->
            when (char) {
                '\"' -> append("\\\"")
                '\\' -> append("\\\\")
                '\b' -> append("\\b")
                '\n' -> append("\\n")
                '\r' -> append("\\r")
                '\t' -> append("\\t")
                else -> append(char)
            }
        }
    }
}
