package com.potatos.stackoverflow.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //매개변수로 입력받음
        http
                .csrf().disable()
                .formLogin().disable() // 폼 기반 로그인 인증 지원
                .httpBasic().disable() // http 기본인증 지원 > 그게 뭔데
                .authorizeHttpRequests(
                        authorize ->
                                authorize.anyRequest().authenticated() //인증된 요청에 대해서만 접근이 허용하도록.

                        //인증된 요청이란?
                )
                .oauth2Login(withDefaults());    // OAuth2 인증을 활성화한다.
        return http.build();
    }
}
