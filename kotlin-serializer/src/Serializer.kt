interface Serializer {
    fun toJson(data: Map<String, Any>): String
    fun fromJson(json: String): Map<String, Any>
}