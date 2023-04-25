package com.potatos.stackoverflow.oauth.config;

import com.potatos.stackoverflow.domain.member.service.MemberService;
import com.potatos.stackoverflow.oauth.filter.JwtVerificationFilter;
import com.potatos.stackoverflow.oauth.handler.MemberAccessDeniedHandler;
import com.potatos.stackoverflow.oauth.handler.MemberAuthenticationEntryPoint;
import com.potatos.stackoverflow.oauth.handler.OAuth2MemberSuccessHandler;
import com.potatos.stackoverflow.oauth.token.JwtTokenizer;
import com.potatos.stackoverflow.oauth.utils.CustomAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration3 {


    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;

    public SecurityConfiguration3(JwtTokenizer jwtTokenizer,
                                 CustomAuthorityUtils authorityUtils,
                                 MemberService memberService) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.memberService = memberService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable() //CSRF 공격에 대한 spring security 설정 비활성화
                .cors(withDefaults()) //CORS 설정 추가 : corsConfigurationSource라는 이름으로 등록된 bean을 이용한다
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //세션을 생성하지 않도록 설정한다.
                //ALWAYS : 항상 생성 / NEVER : 생성X, 만약 이미 생성된 세션있으면 사용 / IF_REQUIRED : 필요한 경우 생성 / STATELESS : 생성X
                .and()
                .formLogin().disable() //폼 로그인 인증 방식 :SSR 방식 / Json포맷으로 할거라서 비활성화 시킨다 :CSR 방식
                .httpBasic().disable() //request 전송할때마다 username/password 정보를 http header에 실어서 인증하는 방식 : 비활성화
                .exceptionHandling()  // 추가
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())  //인증이 되지 않은 유저가 요청을 했을 때 동작된다.
                .accessDeniedHandler(new MemberAccessDeniedHandler())            //서버에 요청을 할 때 액세스가 가능한지 권한을 체크 후 액세스 할 수 없는 요청을 했을 시 동작된다.
                .and()
                .apply(new CustomFilterConfigurer())  // 하단에서 선언한 메서드
                .and()
                .authorizeHttpRequests(authorize -> authorize // url authorization 전체 추가
//                        .antMatchers(HttpMethod.POST, "/*/members").permitAll()    // OAuth 2로 로그인하므로 회원 정보 등록 필요 없음.
//                        .antMatchers(HttpMethod.PATCH, "/*/members/**").hasRole("USER") // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
//                        .antMatchers(HttpMethod.GET, "/*/members").hasRole("ADMIN")  // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
//                        .antMatchers(HttpMethod.GET, "/*/members/**").hasAnyRole("USER", "ADMIN")  // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
//                        .antMatchers(HttpMethod.DELETE, "/*/members/**").hasRole("USER") // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
                                //순서가 좀 중요하다.
                                .antMatchers(HttpMethod.DELETE, "/*/users/*").hasRole("USER") //유저 삭제 (본인)
                                .antMatchers(HttpMethod.POST, "/*/logout/*").hasRole("USER") //유저 로그아웃 (본인)
                                .antMatchers(HttpMethod.POST, "/*/members/edit/*").hasRole("USER") //마이페이지 수정 (본인)
                                .antMatchers(HttpMethod.POST, "/*/questions/*").hasRole("USER") //질문/답변 작성 (본인)
                                .antMatchers(HttpMethod.PATCH, "/*/questions/*").hasRole("USER") //질문 수정 (본인)
                                .antMatchers(HttpMethod.DELETE, "/*/questions/*").hasRole("USER") //질문 삭제 (본인)
                                .antMatchers(HttpMethod.GET, "/*/questions").permitAll()
                                .antMatchers(HttpMethod.POST, "/*/users").permitAll()
                                .antMatchers(HttpMethod.POST, "/*/members").permitAll()
                                .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberService))  // (1)
                );
                // OAuth2 로그인 설정에 .successHandler()에 핸들러를 설정한다.
                // 만든 OAuth2MemberSuccessHandler 선언

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    // 추가
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class); // (2)
            // JwtVerficationFilter를 OAuth2LoginAuthenticationFilter 뒤에 추가한다.
            // 해당 OAuth2LoginAuthenticationFilter 필터가 이미 존재하는가? 내가 생성하는게 아닌게 맞는가?
        }
    }
}
