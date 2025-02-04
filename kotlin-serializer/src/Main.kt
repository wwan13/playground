import v2.fromJson
import v2.toJson

fun main() {
    val json = data.toJson()
    println(json)

    val map = json.fromJson()
    println(map)
}

val data: Map<String, Any?> = mapOf(
    "name" to "John \"The Legend\" Doe",
    "age" to 30L,
    "address" to mapOf(
        "street" to "123 \"Main\" St\nNew Line",
        "city" to "Any\tTown",
        "postalCode" to "12345",
        "geo" to mapOf(
            "latitude" to 37.7749,
            "longitude" to -122.4194
        )
    ),
    "phones" to listOf(
        mapOf("type" to "home", "number" to "123-456-7890"),
        mapOf("type" to "work", "number" to "098-765-4321"),
        mapOf("type" to "mobile", "number" to "+1-555-1234", "verified" to true)
    ),
    "isActive" to true,
    "nullValue" to null,
    "preferences" to mapOf(
        "notifications" to mapOf(
            "email" to true,
            "sms" to false,
            "push" to true
        ),
        "theme" to "dark"
    ),
    "history" to listOf(
        mapOf(
            "date" to "2024-02-01",
            "action" to "login",
            "location" to mapOf(
                "ip" to "192.168.1.1",
                "device" to "Macbook Pro"
            )
        ),
        mapOf(
            "date" to "2024-02-02",
            "action" to "purchase",
            "details" to mapOf(
                "item" to "Laptop",
                "price" to 1299.99,
                "currency" to "USD"
            )
        )
    ),
    "metadata" to mapOf(
        "createdAt" to "2024-01-01T12:00:00Z",
        "updatedAt" to "2024-02-01T15:30:00Z",
        "tags" to listOf("premium", "active", "subscribed"),
        "unicodeTest" to "\u2603\uD834\uDD1E😊",
        "jsonEscapeTest" to "{\"escaped\": \"\\\"quotes\\\" and \\n new lines\"}"
    ),
    "nestedLists" to listOf(
        listOf("a", "b", "c"),
        listOf(
            mapOf("id" to 1, "value" to "A"),
            mapOf("id" to 2, "value" to "B")
        )
    ),
    "settings" to mapOf(
        "volume" to 75,
        "brightness" to 50,
        "languages" to listOf("English", "Korean", "Japanese"),
        "betaFeatures" to mapOf(
            "newUI" to true,
            "AIRecommendations" to false
        ),
        "weirdKeys" to mapOf(
            " space key " to "value with space",
            "new\nline" to "value\nwith\nnew\nlines",
            "tab\tkey" to "value\twith\ttabs",
            "\"quotedKey\"" to "value \"with quotes\"",
            "back\\slash" to "value\\with\\backslashes"
        )
    ),
    "statistics" to mapOf(
        "loginCount" to 124,
        "purchaseCount" to 5,
        "averageSessionTime" to 15.6
    )
)

