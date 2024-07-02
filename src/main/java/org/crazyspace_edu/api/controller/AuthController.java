package org.crazyspace_edu.api.controller;

import org.crazyspace_edu.api.request.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("api/signup")
    public ResponseEntity<String> signUp(SignUpRequest signUpRequest){
        try{

        }catch (Exception e){

        }
        return ResponseEntity.ok("200");
    }
}
