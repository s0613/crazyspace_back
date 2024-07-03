package org.crazyspace_edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.crazyspace_edu.api.request.LoginRequest;
import org.crazyspace_edu.api.request.SignUpRequest;
import org.crazyspace_edu.api.response.LoginResponse;
import org.crazyspace_edu.api.service.AuthService;
import org.crazyspace_edu.api.service.SignUpService;
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
    private final SignUpService signUpService;

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

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(SignUpRequest signUpRequest){
        signUpService.signup(signUpRequest);
        return ResponseEntity.ok("200");
    }
}
