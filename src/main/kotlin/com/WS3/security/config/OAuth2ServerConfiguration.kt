package com.WS3.security.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
class OAuth2ServerConfiguration {
    @Configuration
    @EnableResourceServer
    protected class ResourceServerConfiguration(jwtAccessTokenConverter: JwtAccessTokenConverter) :
        ResourceServerConfigurerAdapter() {
        private val jwtAccessTokenConverter: JwtAccessTokenConverter
        override fun configure(resources: ResourceServerSecurityConfigurer) {
            resources
                .tokenStore(JwtTokenStore(jwtAccessTokenConverter))
        }

        //@Throws(Exception::class)
        override fun configure(http: HttpSecurity) {
            http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/hello").permitAll()
                .anyRequest()
                .authenticated()
        }

        init {
            this.jwtAccessTokenConverter = jwtAccessTokenConverter
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected class AuthorizationServerConfiguration(
        jwtAccessTokenConverter: JwtAccessTokenConverter,
        passwordEncoder: BCryptPasswordEncoder,
        authenticationManager: AuthenticationManager
    ) : AuthorizationServerConfigurerAdapter() {
        private val jwtAccessTokenConverter: JwtAccessTokenConverter
        private val passwordEncoder: BCryptPasswordEncoder
        private val authenticationManager: AuthenticationManager
        override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
            endpoints
                .tokenStore(JwtTokenStore(jwtAccessTokenConverter))
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter)
        }

        //@Throws(Exception::class)
        override fun configure(clients: ClientDetailsServiceConfigurer) {
            clients
                .inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .accessTokenValiditySeconds(120)
        }

        init {
            this.jwtAccessTokenConverter = jwtAccessTokenConverter
            this.passwordEncoder = passwordEncoder
            this.authenticationManager = authenticationManager
        }
    }
}