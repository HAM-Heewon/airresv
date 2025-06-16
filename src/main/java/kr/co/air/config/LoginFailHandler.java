package kr.co.air.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        
        System.out.println("=== LoginFailHandler 실행됨!!! ===");
        System.out.println("Exception 타입: " + exception.getClass().getSimpleName());
        System.out.println("Exception 메시지: " + exception.getMessage());
        
        String errorMessage;

        if (exception instanceof BadCredentialsException) {
            errorMessage = "아이디 또는 비밀번호가 올바르지 않습니다.";
        } else if (exception instanceof InternalAuthenticationServiceException ||
                   exception instanceof LockedException ||
                   exception instanceof DisabledException) {
            errorMessage = exception.getMessage();
        } else {
            errorMessage = "로그인에 실패하였습니다. 관리자에게 문의하세요.";
        }

        System.out.println("최종 에러 메시지: " + errorMessage);
        
        String encodedMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        String redirectUrl = request.getContextPath() + "/login?error=true&message=" + encodedMessage;
        
        System.out.println("리다이렉트 URL: " + redirectUrl);
        
        response.sendRedirect(redirectUrl);
    }
}
