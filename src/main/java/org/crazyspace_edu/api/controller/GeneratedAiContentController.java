package org.crazyspace_edu.api.controller;

import org.crazyspace_edu.api.domain.user.User;
import org.crazyspace_edu.api.repository.UserRepository;
import org.crazyspace_edu.api.request.AiContentRequest;
import org.crazyspace_edu.api.response.AiContentResponse;
import org.crazyspace_edu.api.service.GeneratedAiContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GeneratedAiContentController {
    private static final Logger log = LoggerFactory.getLogger(GeneratedAiContentController.class);
    private final GeneratedAiContentService contentService;
    private final UserRepository userRepository;

    @Autowired
    public GeneratedAiContentController(GeneratedAiContentService contentService, UserRepository userRepository) {
        this.contentService = contentService;
        this.userRepository = userRepository;
    }

    @PostMapping("/generatedAiContents")
    public ResponseEntity<String> createContent(@RequestBody AiContentRequest aiContentRequest) {
        try {
            // 현재 인증된 사용자 정보 가져오기
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null) {
                log.error("Authentication is null");
                return new ResponseEntity<>("Authentication is null", HttpStatus.UNAUTHORIZED);
            }

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if (userDetails == null) {
                log.error("UserDetails is null");
                return new ResponseEntity<>("UserDetails is null", HttpStatus.UNAUTHORIZED);
            }

            String email = userDetails.getUsername();

            // 사용자 ID 가져오기 (예시: userService를 통해 사용자 정보 조회)
            Optional<User> user = userRepository.findByEmail(email);
            if (!user.isPresent()) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            log.info("User found: {}", user);
            Long userId = user.get().getId();

            contentService.createContent(userId, aiContentRequest);
            return ResponseEntity.ok("AiContent created successfully");
        } catch (Exception e) {
            log.error("Error creating content: {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
