package com.potatos.stackoverflow.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potatos.stackoverflow.domain.member.entity.Member;
import com.potatos.stackoverflow.auth.dto.LoginDto;
import com.potatos.stackoverflow.auth.token.JwtTokenizer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//엔트리 포인트 역할을 한다.
//(질문)빈으로 등록은 안하네?
//UsernamePasswordAuthenticationFilter 상속받고있다. 폼 로그인 방식 디폴트 필터
//폼 로그인이 아니더라도 username/password 기반의 인증을 처리하기 위해서 확장가능하다.
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {  // (1)
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;

    // (2) DI주입받기
    //authenticationManager는 로그인 인증 정보(username/password) 전달받아 UserDetailsService와 인터랙션한 뒤 인증 여부를 판단한다.
    //클라이언트가 인증에 성공하면 JwtTokenizer가 JWT를 생성 및 발급한다.
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenizer jwtTokenizer) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenizer = jwtTokenizer;
    }

    // (3) 메서드 내부에서 인증을 시도하는 로직
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();    // (3-1)
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class); // (3-2)
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);  // (3-4)
    }

    // (4) 인증에 성공할 경우 호출된다.
    // AbstractAuthenticationProcessingFilter에서 호출한다고 뜬다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws ServletException, IOException {
        Member member = (Member) authResult.getPrincipal();
        String accessToken = delegateAccessToken(member);   //밑에 함수 선언되어 있다.
        String refreshToken = delegateRefreshToken(member); //밑에 함수 선언되어 있다.

        log.info("하늘:Bearer " + accessToken);
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);
        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);  // (1) 추가
    }

    // delegate 는 위임한다라는 건데 이건 왜 이런 함수명을 갖게된거죠?
    private String delegateAccessToken(Member member) {
        //access token 생성시 필요1
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getEmail());
        claims.put("roles", List.of("USER"));
        //여기까지 claims 구성...


        //access token 생성시 필요2
        String subject = member.getEmail();

        //access token 생성시 필요3
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        //application.yml에서 가져와 accessTokenExpirationMinutes 필드에 저장한 값을 가져온다.

        //access token 생성시 필요4
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        //secret key를 base64형식으로 인코딩하기


        //access token 생성하기!
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);


        return accessToken;
    }

    // (6)
    private String delegateRefreshToken(Member member) {
        //refresh token 생성시 필요1
        String subject = member.getEmail();

        //refresh token 생성시 필요2
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());

        //refresh token 생성시 필요3
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        //refresh token 생성하기!
        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}