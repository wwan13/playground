object RecursionSerializer : Serializer {
    // serializing
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

    // deserializing
    override fun fromJson(json: String): Map<String, Any?> {
        val iterator = json.trim().iterator()
        return parseObject(iterator, true)
    }

    private fun parseObject(
        iterator: CharIterator,
        skipFirst: Boolean = false,
    ): Map<String, Any?> {
        val map = mutableMapOf<String, Any?>()

        if (skipFirst) {
            iterator.skip('{')
        }

        while (iterator.hasNext()) {
            val key = parseString(iterator, true)
            iterator.skip(':')
            map[key] = parseValue(iterator)

            when (iterator.next()) {
                '}' -> return map
                ',', '"' -> continue
                else -> throw IllegalArgumentException("Invalid JSON object")
            }
        }
        throw IllegalArgumentException("Unclosed JSON object")
    }

    private fun parseValue(iterator: CharIterator): Any? {
        while (iterator.hasNext()) {
            when (val c = iterator.next()) {
                '"' -> return parseString(iterator)
                't' -> return parseBoolean(iterator, true)
                'f' -> return parseBoolean(iterator, false)
                'n' -> return parseNull(iterator)
                '{' -> return parseObject(iterator)
                '[' -> return parseArray(iterator)
                in '0'..'9', '-' -> return parseNumber(iterator, c)
            }
        }
        throw IllegalArgumentException("Invalid JSON value")
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

    private fun parseString(
        iterator: CharIterator,
        isKey: Boolean = false,
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

    private fun parseBoolean(iterator: CharIterator, value: Boolean): Boolean {
        return if (isNext(iterator, 'r', 'u', 'e')) {
            true
        } else if (isNext(iterator, 'a', 'l', 's', 'e')) {
            false
        } else {
            throw IllegalArgumentException("Invalid JSON boolean")
        }
    }

    private fun parseNull(iterator: CharIterator): Any? {
        return if (isNext(iterator, 'u', 'l', 'l')) {
            null
        } else {
            throw IllegalArgumentException("Invalid JSON null")
        }
    }

    private fun parseNumber(iterator: CharIterator, c: Char): Number {
        return StringBuilder().let {
            it.append(c)
            while (iterator.hasNext()) {
                val ch = iterator.next()
                if (ch in "0123456789.-eE") it.append(ch) else break
            }
            val stringValue = it.toString()
            stringValue.toDoubleOrNull() ?: stringValue.toLong()
        }
    }

    private fun isNext(
        iterator: CharIterator,
        vararg targets: Char,
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
