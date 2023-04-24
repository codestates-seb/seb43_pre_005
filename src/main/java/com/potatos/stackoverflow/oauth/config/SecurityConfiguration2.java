package com.potatos.stackoverflow.oauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@PropertySource(value = "application.yml")
//@Configuration
public class SecurityConfiguration2 {
    @Value("${spring.security.oauth2.client.registration.google.client-id}")  // (1)
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}") // (2)
    private String clientSecret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .oauth2Login(withDefaults());
        return http.build();
    }

    // 이 하위 작업들을 왜 했는데? 자동설정안하고 configuration으로도 가능하다!
    // 의존성 주입 : implementation 'org.springframework.boot:spring-boot-starter-oauth2-client 만 해주면 자동완성해준다
    // 그런데 이줄 없애니까 ClientRegistrationRepository랑 ClientRegistration 못 가져오는데..? 흠..
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        var clientRegistration = clientRegistration();    // (3-1)

        // client registration(등록) 을 저장하기 위한 저장소
        return new InMemoryClientRegistrationRepository(clientRegistration);
        // 구현체(ClientRegistration 인터페이스의 구현 클래스의 인스턴스)
        // 저장소 생성자가 호출되면서 clientRegistration을 메모리에 저장한다
    }

    // (4)
    private ClientRegistration clientRegistration() {
        // 스프링 세큐리티 라이브러리에서 제공하는 Enum
        return CommonOAuth2Provider
                .GOOGLE
                .getBuilder("google")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build(); //빌더 패턴 : 복잡한 객체들을 단계별로 생성할 수 있도록 하는 생성 디자인 패턴
    }
}
