package com.potatos.stackoverflow.auth.config;

import com.potatos.stackoverflow.domain.member.service.MemberService;
import com.potatos.stackoverflow.auth.filter.JwtAuthenticationFilter;
import com.potatos.stackoverflow.auth.filter.JwtVerificationFilter;
import com.potatos.stackoverflow.auth.handler.*;
import com.potatos.stackoverflow.auth.token.JwtTokenizer;
import com.potatos.stackoverflow.auth.utils.CustomAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .headers().frameOptions().sameOrigin() // 동일 출처로부터 들어오는 request만 페이지 렌더링을 적용한다.
//                .and()
//                .csrf().disable() //CSRF 공격에 대한 spring security 설정 비활성화
//                .cors(withDefaults()) //CORS 설정 추가 : corsConfigurationSource라는 이름으로 등록된 bean을 이용한다
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                //세션을 생성하지 않도록 설정한다.
//                //ALWAYS : 항상 생성 / NEVER : 생성X, 만약 이미 생성된 세션있으면 사용 / IF_REQUIRED : 필요한 경우 생성 / STATELESS : 생성X
//                .and()
//                .formLogin().disable() //폼 로그인 인증 방식 :SSR 방식 / Json포맷으로 할거라서 비활성화 시킨다 :CSR 방식
//                .httpBasic().disable() //request 전송할때마다 username/password 정보를 http header에 실어서 인증하는 방식 : 비활성화
//                .exceptionHandling()  // 추가
//                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())  //인증이 되지 않은 유저가 요청을 했을 때 동작된다.
//                .accessDeniedHandler(new MemberAccessDeniedHandler())            //서버에 요청을 할 때 액세스가 가능한지 권한을 체크 후 액세스 할 수 없는 요청을 했을 시 동작된다.
//                .and()
//                .apply(new CustomFilterConfigurer())  // 하단에서 선언한 메서드
//                //jwtVerificationFilter 추가함.
//                .and()
//                .authorizeHttpRequests(authorize -> authorize // url authorization 전체 추가
////                        .antMatchers(HttpMethod.POST, "/*/members").permitAll()    // OAuth 2로 로그인하므로 회원 정보 등록 필요 없음.
////                        .antMatchers(HttpMethod.PATCH, "/*/members/**").hasRole("USER") // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
////                        .antMatchers(HttpMethod.GET, "/*/members").hasRole("ADMIN")  // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
////                        .antMatchers(HttpMethod.GET, "/*/members/**").hasAnyRole("USER", "ADMIN")  // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
////                        .antMatchers(HttpMethod.DELETE, "/*/members/**").hasRole("USER") // OAuth 2로 로그인하므로 회원 정보 수정 필요 없음.
//                                //순서가 좀 중요하다.
////                                .antMatchers(HttpMethod.DELETE, "/*/users/*").hasRole("USER") //유저 삭제 (본인)
////                                .antMatchers(HttpMethod.POST, "/*/logout/*").hasRole("USER") //유저 로그아웃 (본인)
////                                .antMatchers(HttpMethod.POST, "/*/members/edit/*").hasRole("USER") //마이페이지 수정 (본인)
////                                .antMatchers(HttpMethod.POST, "/*/questions/*").hasRole("USER") //질문/답변 작성 (본인)
////                                .antMatchers(HttpMethod.PATCH, "/*/questions/*").hasRole("USER") //질문 수정 (본인)
////                                .antMatchers(HttpMethod.DELETE, "/*/questions/*").hasRole("USER") //질문 삭제 (본인)
//                                .antMatchers(HttpMethod.GET, "/*/users/*").hasRole("ADMIN") //유저 1명 조회 (본인)
////                                .antMatchers(HttpMethod.GET, "/*/questions").permitAll()
////                                .antMatchers(HttpMethod.POST, "/*/users").permitAll()
////                                .antMatchers(HttpMethod.POST, "/*/members").permitAll()
//                                .anyRequest().permitAll()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberService))  // (1)
//                );
//                // OAuth2 로그인 설정에 .successHandler()에 핸들러를 설정한다.
//                // 만든 OAuth2MemberSuccessHandler 선언
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() // (1) h2콘솔 사용위해서 / 동일 출처로부터 들어오는 request만 페이지 렌더링을 허용한다.
                .and()
                .csrf().disable()        // (2) CSRF 공격에 대한 spring security 설정 비활성화
                .cors(withDefaults())    // (3) CORS 설정 추가 : corsConfigurationSource라는 이름으로 등록된 bean을 이용한다
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //세션을 생성하지 않도록 설정한다.
                //ALWAYS : 항상 생성 / NEVER : 생성X, 만약 이미 생성된 세션있으면 사용 / IF_REQUIRED : 필요한 경우 생성 / STATELESS : 생성X
                .and()
                .formLogin().disable()   // (4) 폼 로그인 인증 방식 :SSR 방식 / Json포맷으로 할거라서 비활성화 시킨다 :CSR 방식
                .httpBasic().disable()   // (5) request 전송할때마다 username/password 정보를 http header에 실어서 인증하는 방식 : 비활성화
                .apply(new CustomFilterConfigurer())// V2 : 내가 만든 사용자 정의 구성을 적용하는 메서드
                // apply(파라미터 타입 : AbstractHttpConfigurer)
                // 그리고 이 객체의 configure 함수에서 내가 지정한 필터체인을 추가하는 거다.
                .and()
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().permitAll()
//                        // (6) JWT를 적용하기 전이라서 모든 HTTP request 요청에 대해서 접근을 허용하도록 설정한다.
                .authorizeHttpRequests(authorize->authorize
                        .antMatchers(HttpMethod.GET, "/**/users/**").hasRole("USER") //유저 1명 조회 (본인)
                                .antMatchers(HttpMethod.DELETE, "/**/users/**").hasRole("USER") //유저 삭제 (본인)
                                .antMatchers(HttpMethod.POST, "/**/logout/**").hasRole("USER") //유저 로그아웃 (본인)
                                .antMatchers(HttpMethod.POST, "/**/members/edit/**").hasRole("USER") //마이페이지 수정 (본인)
                                .antMatchers(HttpMethod.POST, "/**/questions/**").hasRole("USER") //질문/답변 작성 (본인)
                                .antMatchers(HttpMethod.PATCH, "/**/questions/**").hasRole("USER") //질문 수정 (본인)
                                .antMatchers(HttpMethod.DELETE, "/**/questions/**").hasRole("USER") //질문 삭제 (본인)
                                .antMatchers(HttpMethod.GET, "/**/users/**").hasRole("ADMIN") //유저 1명 조회 (본인)
                                //이거 오류났음 고쳐야함.
                                .anyRequest().permitAll()
                );
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

    // (7) passwordEncoder bean객체 생성

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // HttpSecurity는 http요청에 대한 보안 구성을 제공하는 클래스이다.
    // > 보안 필터 체인 구성/인증 및 인가에 대한 규칙 설정/사용자가 접근할 수 있는 URL 및 리소스 지정
    // 위에서 필터체인에서 HttpSecurity객체로 build()한다.
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            // Configuration을 커스텀마이징한다.
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            // AuthenticationManager객체를 얻어온다.
            // Spring Security의 설정을 구성하는 SecurityConfigurer간에 공유되는 객체를 얻을 수 있다

            //JwtAuthenticationFilter 생성하고 DI해주기
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);  //
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());  //인증 성공시 핸들러 적용
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());  // 인증 실패시 핸들러 적용

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);  //

            builder
                    .addFilter(jwtAuthenticationFilter)
                    // 필터체인에 추가. 근데 위에 apply는 뭐지?
                    // apply메서드는 사용자 정의 구성을 설정하는 메서드이고
                    // addFilter메서든 단순히 필터만 추가하는 메서드이다.
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
            //JwtAuthenticationFilter 타입객체 후에 jwtVerificationFilter를 이어서 하는 걸로 고정시킨다.
//            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);
//
//            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class); // (2)
//            // JwtVerficationFilter를 OAuth2LoginAuthenticationFilter 뒤에 추가한다.
//            // 해당 OAuth2LoginAuthenticationFilter 필터가 이미 존재하는가? 내가 생성하는게 아닌게 맞는가?
        }
    }
}
