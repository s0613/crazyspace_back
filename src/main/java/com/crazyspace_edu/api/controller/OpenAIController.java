package com.crazyspace_edu.api.controller;


import com.crazyspace_edu.api.domain.ChatMessage;
import com.crazyspace_edu.api.domain.user.User;
import com.crazyspace_edu.api.repository.ChatMessageRepository;
import com.crazyspace_edu.api.repository.UserRepository;
import com.crazyspace_edu.api.request.AiContentRequest;
import com.crazyspace_edu.api.response.AiContentResponse;
import com.crazyspace_edu.api.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenAIController {
    private final OpenAIService openAIService;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @PostMapping("/converse/ai")
    public ResponseEntity<AiContentResponse> createContent(@RequestBody AiContentRequest aiContentRequest) {
        AiContentResponse response = openAIService.callOpenAiService(aiContentRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chat/messages")
    public ResponseEntity<List<ChatMessage>> getMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        List<ChatMessage> messages = chatMessageRepository.findByUserId(user.getId());
        return ResponseEntity.ok(messages);
    }
}
