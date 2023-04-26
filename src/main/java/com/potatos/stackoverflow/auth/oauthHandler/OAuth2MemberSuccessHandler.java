package com.potatos.stackoverflow.auth.oauthHandler;

import com.potatos.stackoverflow.domain.member.entity.Member;
import com.potatos.stackoverflow.domain.member.service.MemberService;
import com.potatos.stackoverflow.auth.token.JwtTokenizer;
import com.potatos.stackoverflow.auth.utils.CustomAuthorityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// OAuth2 인증후, 프론트엔드 애플리케이션 쪽으로 JWT 전송
@Slf4j
public class OAuth2MemberSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // SimpleUrlAuthenticationSuccessHandler  >> redirect API 사용가능

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;

    // (2)
    public OAuth2MemberSuccessHandler(JwtTokenizer jwtTokenizer,
                                      CustomAuthorityUtils authorityUtils,
                                      MemberService memberService) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.memberService = memberService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var oAuth2User = (OAuth2User)authentication.getPrincipal();
        //Authentication -> OAuth2User -> 이메일 주소 접근
        String email = String.valueOf(oAuth2User.getAttributes().get("email"));
        System.out.println("email:"+email);
        //이메일 주소로만 권한을 만들 수 있나?
        //이메일말고 또 가질수 있는게 있나? name 이런거
        //컨트롤 + b로 내부 소스코드 봤는데 안보여
        //구글링 해보기


        List<String> authorities = authorityUtils.createRoles(email);
        //CustomAuthorityUtils > 권한 정보 생성 > USER 받아옴(이거밖에 안함)

        saveMember(email);
        // 애플리케이션쪽에서 일부 데이터로 관리해야 한다. : 하단에 선언
        redirect(request, response, email, authorities); // 권한 정보 받아서 새 요청 만들기
        // 리다이렉트 메서드 : 하단에 선언
    }

    private void saveMember(String email) {
        Member member = Member.of(email);
        //필요한 최소한의 데이터가 뭘까
        //어느정보까지 스프링 컨텍스트에 저장될까?

        //로그인할 때마다 저장한다?
        //로그인할 때마다 저장하기보단
        //2가지 방법이 있지.
        //1. 회원가입 요청일때만 저장
        //2. 매번 로그인 할 때마다 가져온 이메일 주소를 db에서 검색해서 없는 주소면 회원가입 절차를 통해서 DB에 저장하고,
        //   있는 주소면 따로 작업 안하면 될거 같은데?
        memberService.saveOAuthMember(member);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String username, List<String> authorities) throws IOException {
        //username이 사실상 email
        String accessToken = delegateAccessToken(username, authorities);  // access token 생성
        String refreshToken = delegateRefreshToken(username);     // refresh token 생성

        String uri = createURI(accessToken, refreshToken).toString();   // : 하단에 선언
        getRedirectStrategy().sendRedirect(request, response, uri);   // -> URL 만들어서 요청(리다이렉트)
    }


    // 여기 메서드들 무슨 동작하는지 찾아봐야한다.

    private String delegateAccessToken(String username, List<String> authorities) {
        //email과 USER 받아옴
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", authorities);
        //JWT에 넣을 클레임 생성하기

        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        //JWT에 넣을 만기 만들기

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        //string key -> base64 key

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
        //access token만들기

        return accessToken;
    }

    private String delegateRefreshToken(String username) {

        //access token과는 다르게 claims를 넣지 않는다.

        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .path("/users/login-success")
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}
