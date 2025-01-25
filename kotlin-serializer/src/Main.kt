fun main() {
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

    val jsonValue = RecursionSerializer.toJson(data)
    println(jsonValue)
}