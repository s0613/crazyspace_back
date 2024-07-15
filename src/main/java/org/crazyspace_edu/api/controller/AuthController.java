package org.crazyspace_edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.crazyspace_edu.api.request.LoginRequest;
import org.crazyspace_edu.api.request.SignUpRequest;
import org.crazyspace_edu.api.response.LoginResponse;
import org.crazyspace_edu.api.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authService.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(new LoginResponse("로그인 성공", authentication.getName()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new LoginResponse("로그인 실패", null));
        }
    }

    @GetMapping("/auth/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String token) {
        boolean isVerified = authService.verifyUser(token);
        if (isVerified) {
            return ResponseEntity.ok("Email verified successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid token or token expired!");
        }
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {
            authService.signup(signUpRequest);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            // 예외 처리 및 로깅
            e.printStackTrace();
            return new ResponseEntity<>("ㅅㅂ Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
