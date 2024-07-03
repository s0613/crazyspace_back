package org.crazyspace_edu.api.controller;

import lombok.RequiredArgsConstructor;
import org.crazyspace_edu.api.request.SignUpRequest;
import org.crazyspace_edu.api.service.SignUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(SignUpRequest signUpRequest){
        signUpService.signup(signUpRequest);
        return ResponseEntity.ok("200");
    }
}
