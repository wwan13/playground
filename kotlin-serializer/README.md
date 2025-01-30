## Kotlin Serializer

### Result

```plaintext
=== ToJson ===
{"name":"John Doe","age":30,"address":{"street":"123 Main St","city":"Anytown","postalCode":"12345"},"phones":[{"type":"home","number":"123-456-7890"},{"type":"work","number":"098-765-4321"}],"isActive":true,"nullValue":null}
===============

=== FromJson ===
{name=John Doe, age=30.0, ddress={street=123 Main St, city=Anytown, postalCode=12345}, phones=[{type=home, number=123-456-7890}, {type=work, number=098-765-4321}], isActive=true, nullValue=null}
===============
```