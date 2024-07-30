package com.crazyspace_edu.api.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.crazyspace_edu.api.config.UserPrincipal;
import com.crazyspace_edu.api.util.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    final private ObjectMapper objectMapper;
    final private JwtUtil jwtUtil;
    final private String secret;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        log.info("[인증성공] user={}", principal.getUsername());

        try {
            String jwt = jwtUtil.generateToken(principal.getUsername(), secret, 3600000000000L);
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(UTF_8.name());
            response.setStatus(SC_OK);

            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", jwt);
            response.getWriter().write(objectMapper.writeValueAsString(tokenMap));

            log.info("[JWT 생성 성공] jwt={}", jwt);
        } catch (Exception e) {
            log.error("[JWT 생성 실패]", e);
            throw new ServletException("Authentication success but failed to create JWT", e);
        }
    }
}

