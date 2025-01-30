fun main() {
    val serializer = RecursionSerializer

    executeToJson(serializer)
    println()
    executeFromJson(serializer)
}

fun executeToJson(serializer: Serializer) {
    val data: Map<String, Any?> = mapOf(
        "name" to "John Doe",
        "age" to 30L,
        "address" to mapOf(
            "street" to "123 Main St",
            "city" to "Anytown",
            "postalCode" to "12345"
        ),
        "phones" to listOf(
            mapOf("type" to "home", "number" to "123-456-7890"),
            mapOf("type" to "work", "number" to "098-765-4321")
        ),
        "isActive" to true,
        "nullValue" to null
    )

    val jsonValue = serializer.toJson(data)
    println("=== ToJson ===")
    println(jsonValue)
    println("===============")
}

fun executeFromJson(serializer: Serializer) {
    val json = """
        {"name":"John Doe","age":30,"address":{"street":"123 Main St","city":"Anytown","postalCode":"12345"},"phones":[{"type":"home","number":"123-456-7890"},{"type":"work","number":"098-765-4321"}],"isActive":true,"nullValue":null}
    """.trimIndent()

    val data = serializer.fromJson(json)
    println("=== FromJson ===")
    println(data.toString())
    println("===============")
}