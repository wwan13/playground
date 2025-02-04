package v2

fun String.fromJson(): Map<String, Any?> {
    return readObject(Json(this.trim()), true)
}

class Json(
    val value: String,
    var cursor: Int = 0,
) {
    val length: Int
        get() = value.length
}

private fun readObject(
    json: Json,
    isRoot: Boolean
): Map<String, Any?> {
    val map = mutableMapOf<String, Any?>()

    if (isRoot && json.value[json.cursor++] != '{') {
        throw IllegalArgumentException("Invalid JSON object")
    }

    while (json.cursor < json.length) {
        when (val c = json.value[json.cursor++]) {
            ' ', '\t', '\r', '\n' -> continue
            '"' -> {
                val key = parseString(json)
                if (json.value[json.cursor++] != ':') {
                    throw IllegalArgumentException("Invalid JSON object")
                }
                val value = readValue(json)
                map[key] = value
            }
            ',' -> continue
            '}' -> return map
            else -> {
                println(c)
                throw IllegalArgumentException("Invalid JSON object")
            }
        }
    }
    throw IllegalArgumentException("Invalid JSON object")
}

private fun readValue(json: Json): Any? {
    while (json.cursor < json.length) {
        when (val c = json.value[json.cursor++]) {
            ' ', '\t', '\r', '\n' -> json.cursor++
            '"' -> {
                json.cursor++
                return parseString(json)
            }
            't', 'f', 'n' -> {
                val start = json.cursor - 1
                while (json.cursor < json.length && json.value[json.cursor].isLetter()) {
                    json.cursor++
                }
                return when (json.value.substring(start, json.cursor)) {
                    "true" -> true
                    "false" -> false
                    "null" -> null
                    else -> throw IllegalArgumentException("Invalid JSON keyword")
                }
            }
            '{' -> return readObject(json, false)
            '[' -> return readArray(json)
            in '0'..'9', '-' -> {
                val start = json.cursor - 1
                while (json.cursor < json.length && json.value[json.cursor] !in ",:]} ") {
                    json.cursor++
                }
                return json.value.substring(start, json.cursor).toDoubleOrNull()
                    ?: throw IllegalArgumentException("Invalid JSON number format")
            }
        }
    }
    throw IllegalArgumentException("Invalid JSON value")
}

private fun readArray(json: Json): List<Any?> {
    val list = mutableListOf<Any?>()

    if (json.value[json.cursor - 1] != '[') {
        throw IllegalArgumentException("Invalid JSON array")
    }

    while (json.cursor < json.length) {
        when (val c = json.value[json.cursor]) {
            ' ', '\t', '\r', '\n' -> json.cursor++
            ']' -> {
                json.cursor++
                return list
            }
            ',' -> json.cursor++
            else -> list.add(readValue(json))
        }
    }
    throw IllegalArgumentException("Invalid JSON array")
}

private fun parseString(json: Json): String {
    val start = json.cursor - 1

    while (json.cursor < json.length) {
        if (json.value[json.cursor] == '\\') {
            json.cursor++
        } else if (json.value[json.cursor] == '"') {
            break
        }
        json.cursor++
    }

    if (json.cursor >= json.length) throw IllegalArgumentException("Invalid JSON string")

    val string = json.value.substring(start, json.cursor)
        .replace("\\\"", "\"")
        .replace("\\n", "\n")
        .replace("\\t", "\t")
        .replace("\\\\", "\\")

    json.cursor++
    return string
}
