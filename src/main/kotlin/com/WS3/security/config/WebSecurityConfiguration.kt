package com.WS3.security.config

import com.WS3.security.AccountAuthenticationProvider
import com.WS3.security.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration(
    private val userDetailsService: CustomUserDetailsService,
    private val accountAuthenticationProvider: AccountAuthenticationProvider
) :
    WebSecurityConfigurerAdapter() {
    /*@Autowired
    override fun setApplicationContext(context: ApplicationContext) {
        super.setApplicationContext(context)
        val globalAuthBuilder = context
            .getBean<AuthenticationManagerBuilder>(AuthenticationManagerBuilder::class)
        try {
            globalAuthBuilder.userDetailsService(userDetailsService)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/

    //@Throws(Exception::class)
    @Autowired
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
        auth.authenticationProvider(accountAuthenticationProvider)
    }

    @Bean
    //@Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val jwtAccessTokenConverter = JwtAccessTokenConverter()
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY)
        return jwtAccessTokenConverter
    }

    companion object {
        private const val SIGNING_KEY = "s1f41234pwqdqkl4l12ghg9853123sd"
    }
}