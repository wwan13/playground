package io.wwan13.wintersecuritykotlin.config

import io.wwan13.wintersecurity.auth.authpattern.support.AuthPatternsRegistry
import io.wwan13.wintersecurity.config.EnableJwtProvider
import io.wwan13.wintersecurity.config.EnableSecureRequest
import io.wwan13.wintersecurity.config.JwtProviderConfigurer
import io.wwan13.wintersecurity.config.SecureRequestConfigurer
import io.wwan13.wintersecurity.jwt.support.JwtPropertiesRegistry
import io.wwan13.wintersecurity.secretkey.support.SecretKeyRegistry
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod

@Configuration
@EnableJwtProvider
@EnableSecureRequest
class AuthConfig : SecureRequestConfigurer, JwtProviderConfigurer {

    override fun configureSecretKey(registry: SecretKeyRegistry) {
        // d2ludGVyLXNlY3VyaXR5LWV4YW1wbGUK
        registry
            .secretKey("asdasdasdasdasdasdasdasdasdasdasdasdad")
    }

    override fun registerAuthPatterns(registry: AuthPatternsRegistry) {
        registry.apply {
            uriPatterns("/api/id")
                .httpMethods(HttpMethod.GET)
                .permitAll()

            uriPatterns("/api/admin")
                .allHttpMethods()
                .hasRoles("ROLE_ADMIN")

            elseRequestAuthenticated()
        }
    }

    override fun configureJwt(registry: JwtPropertiesRegistry) {
        registry.apply {
            accessTokenValidity(1000000L)
            refreshTokenValidity(20000000L)
        }
    }
}
