package io.wwan13.wintersecuritykotlin.auth

import io.wwan13.wintersecurity.jwt.payload.annotation.Payload
import io.wwan13.wintersecurity.jwt.payload.annotation.Roles
import io.wwan13.wintersecurity.jwt.payload.annotation.Subject

@Payload
data class AuthPayload(
    @Subject
    val id: Long,
    @Roles
    val role: String,
)
