package com.crazyspace_edu.api.controller;

import com.crazyspace_edu.api.request.AiContentRequest;
import com.crazyspace_edu.api.domain.user.User;
import com.crazyspace_edu.api.repository.UserRepository;
import com.crazyspace_edu.api.response.ContentDTO;
import com.crazyspace_edu.api.service.GeneratedAiContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

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
            if (authentication == null || !authentication.isAuthenticated()) {
                throw new AccessDeniedException("User not authenticated");
            }

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            // 사용자 ID 가져오기 (예시: userService를 통해 사용자 정보 조회)
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            log.info("User found: {}", user);

            Long userId = user.getId();
            contentService.createContent(userId, aiContentRequest);
            return ResponseEntity.ok("AiContent created successfully");
        } catch (UsernameNotFoundException e) {
            log.error("User not found error: {}", e.getMessage());
            return new ResponseEntity<>("error: User not found", HttpStatus.NOT_FOUND);
        } catch (AccessDeniedException e) {
            log.error("Access denied error: {}", e.getMessage());
            return new ResponseEntity<>("error: Access denied", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            log.error("Internal server error: {}", e.getMessage());
            return new ResponseEntity<>("error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/generatedAiContents/top5")
    public ResponseEntity<List<ContentDTO>> getTop5GeneratedAiContents() {
        List<ContentDTO> contents = contentService.getTop5GeneratedContents();
        return ResponseEntity.ok(contents);
    }

}
