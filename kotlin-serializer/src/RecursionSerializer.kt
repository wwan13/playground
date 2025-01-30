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

    private fun writeValue(value: Any?): String {
        return when (value) {
            null -> "null"
            is Boolean -> value.toString()
            is String -> "\"$value\""
            is Long -> value.toString()
            is Map<*, *> -> toJson(value as Map<String, Any?>)
            is List<*> -> writeListValue(value as List<Any?>)
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    private fun writeListValue(list: List<Any?>): String {
        return list.joinToString(
            separator = ",",
            prefix = "[",
            postfix = "]"
        ) { writeValue(it) }
    }

    override fun fromJson(json: String): Map<String, Any?> {
        val iterator = json.trim().iterator()
        return parseObject(iterator)
    }

    private fun parseObject(iterator: CharIterator): Map<String, Any?> {
        // if (iterator.next() != '{') {
        //     throw IllegalArgumentException("Invalid JSON object")
        // }

        val map = mutableMapOf<String, Any?>()

        while (iterator.hasNext()) {
            val key = parseString(iterator, true)

            iterator.skip(':')

            map[key] = parseValue(iterator)

            when (val c = iterator.next()) {
                '}' -> return map
                ',', '"' -> continue
                else -> throw IllegalArgumentException("Invalid JSON object")
            }
        }
        throw IllegalArgumentException("Unclosed JSON object")
    }

    private fun parseArray(iterator: CharIterator): List<Any?> {
        val list = mutableListOf<Any?>()
        while (iterator.hasNext()) {
            list.add(parseValue(iterator))
            when (iterator.next()) {
                ']' -> return list
                ',' -> continue
                else -> throw IllegalArgumentException("Invalid JSON array")
            }
        }
        throw IllegalArgumentException("Unclosed JSON array")
    }

    private fun parseValue(iterator: CharIterator): Any? {
        while (iterator.hasNext()) {
            when (val c = iterator.next()) {
                '"' -> return parseString(iterator)
                't' -> {
                    if (isNext(iterator, 'r', 'u', 'e')) {
                        return true
                    }
                }

                'f' -> {
                    if (isNext(iterator, 'a', 'l', 's', 'e')) {
                        return false
                    }
                }

                'n' -> {
                    if (isNext(iterator, 'u', 'l', 'l')) {
                        return null
                    }
                }

                '{' -> return parseObject(iterator)
                '[' -> return parseArray(iterator)
                in '0'..'9', '-' -> {
                    val number = buildString {
                        append(c)
                        while (iterator.hasNext()) {
                            val ch = iterator.next()
                            if (ch in "0123456789.-eE") append(ch) else break
                        }
                    }
                    return number.toDoubleOrNull() ?: number.toLong()
                }
            }
        }
        throw IllegalArgumentException("Invalid JSON value")
    }

    private fun parseString(
        iterator: CharIterator,
        isKey: Boolean = false
    ): String {
        if (isKey) {
            iterator.next()
        }
        return StringBuilder().apply {
            while (iterator.hasNext()) {
                val ch = iterator.next()
                if (ch == '"') {
                    break
                }
                append(ch)
            }
        }.toString()
    }

    private fun isNext(
        iterator: CharIterator,
        vararg targets: Char
    ): Boolean {
        return targets.all {
            iterator.next() == it
        }
    }

    private fun CharIterator.skip(target: Char) {
        while (this.hasNext() && this.next() != target) {
        }
    }
}
