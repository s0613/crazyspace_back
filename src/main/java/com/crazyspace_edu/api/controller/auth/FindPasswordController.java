package com.crazyspace_edu.api.controller.auth;

import com.crazyspace_edu.api.repository.UserRepository;
import com.crazyspace_edu.api.service.FindPasswordService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/find")
@RequiredArgsConstructor
public class FindPasswordController {
    private final UserRepository userRepository;
    private final FindPasswordService findPasswordService;
    @PostMapping("/password")
    public String findPasswordByEmail(String email) throws MessagingException, UnsupportedEncodingException {
        if(userRepository.findByEmail(email).isPresent()){
            findPasswordService.sendTokenForFindPassword(email);
            return "이메일을 확인해주세요";
        }
        else return "없음";
    }

}
