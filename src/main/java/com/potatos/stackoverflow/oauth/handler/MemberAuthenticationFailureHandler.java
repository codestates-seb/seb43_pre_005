package com.potatos.stackoverflow.oauth.handler;

import com.google.gson.Gson;
import com.potatos.stackoverflow.oauth.utils.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {  // (1)
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // 인증 실패 시, 에러 로그를 기록하거나 error response를 전송할 수 있다.
        log.error("# Authentication failed: {}", exception.getMessage());

        sendErrorResponse(response);  // (2)
    }

    private void sendErrorResponse(HttpServletResponse response) throws IOException {
        Gson gson = new Gson();     // (2-1)
        //원래 컨트롤러에서 응답은 ResponseEntity 인데 여기선 객체 자체가 다르다.
        //ErrorResponse 객체. of()는 생성자라고 생각하면된다. 객체 생성!
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.UNAUTHORIZED); // (2-2)
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);    // (2-3) 응답 타입 json이라고 알려준다
        response.setStatus(HttpStatus.UNAUTHORIZED.value()); // enum타입 UNAUTHORIZED의 value(int) 401을 설정한다.
        // (2-4) 응답 상태 : 인증실패할 경우 401 상태코드를 전달한다.
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
        // (2-5) gson객체를 이용해 ErrorResponse객체를 JSON포맷 문자열로 변환 후, 출력 스트림을 생성한다.
    }
}