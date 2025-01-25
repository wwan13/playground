object RecursionSerializer : Serializer {
    override fun toJson(data: Map<String, Any?>): String {
        return data.entries.joinToString(
            separator = ",",
            prefix = "{",
            postfix = "}"
        ) { (key, value) ->
            "\"$key\":${writeValue(value)}"
        }
    }

    private fun toListValue(list: List<Any?>): String {
        return list.joinToString(
            separator = ",",
            prefix = "[",
            postfix = "]"
        ) { writeValue(it) }
    }

    private fun writeValue(value: Any?): String {
        return when (value) {
            null -> "null"
            is Boolean -> value.toString()
            is String -> "\"$value\""
            is Long -> value.toString()
            is Map<*, *> -> toJson(value as Map<String, Any?>)
            is List<*> -> toListValue(value as List<Any?>)
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    override fun fromJson(json: String): Map<String, Any?> {
        TODO("Not yet implemented")
    }
}