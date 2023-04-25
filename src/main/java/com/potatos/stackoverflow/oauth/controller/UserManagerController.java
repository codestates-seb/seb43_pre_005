package com.potatos.stackoverflow.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserManagerController {

    private final OAuth2AuthorizedClientService auth2AuthorizedClientService;

    public UserManagerController(OAuth2AuthorizedClientService auth2AuthorizedClientService) {
        this.auth2AuthorizedClientService = auth2AuthorizedClientService;
    }
//
//    @GetMapping("/google-oauth2")
//    public String loginMain(){
//
//        System.out.println("login-main");
//
//        var oAuth2User= (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(oAuth2User.getAttributes().get("email"));
//        return "login-page";
//    }

//    @GetMapping("/login2")
//    public String loginHome(Authentication authentication){
//        System.out.println("login-home");
//
//        var authorizedClient=auth2AuthorizedClientService.loadAuthorizedClient("google", authentication.getName());
//
//        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
//        System.out.println("Access Token Value: " + accessToken.getTokenValue());  // (3-1)
//        System.out.println("Access Token Type: " + accessToken.getTokenType().getValue());  // (3-2)
//        System.out.println("Access Token Scopes: " + accessToken.getScopes());       // (3-3)
//        System.out.println("Access Token Issued At: " + accessToken.getIssuedAt());    // (3-4)
//        System.out.println("Access Token Expires At: " + accessToken.getExpiresAt());  // (3-5)
//
//        return "login-oauth";
//    }

    // 위 메서드 작업을 @RegisteredOAuth2AuthorizedClient 애너테이션으로 처리함.
    @GetMapping("/login2")
    public String loginHome(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient){
        System.out.println("login-home");

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println("Access Token Value: " + accessToken.getTokenValue());  // (3-1)
        System.out.println("Access Token Type: " + accessToken.getTokenType().getValue());  // (3-2)
        System.out.println("Access Token Scopes: " + accessToken.getScopes());       // (3-3)
        System.out.println("Access Token Issued At: " + accessToken.getIssuedAt());    // (3-4)
        System.out.println("Access Token Expires At: " + accessToken.getExpiresAt());  // (3-5)

        return "login-oauth";
    }

    @GetMapping("/users/login")
    public String loadLoginPage() {
        System.out.println("유저 로그인 페이지 로드");
        return "login-page";
    }

}