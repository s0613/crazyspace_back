package com.crazyspace_edu.api.controller;


import com.crazyspace_edu.api.domain.ai.ChatMessage;
import com.crazyspace_edu.api.domain.ai.Conversation;
import com.crazyspace_edu.api.domain.user.User;
import com.crazyspace_edu.api.repository.conversation.ConversationRepository;
import com.crazyspace_edu.api.repository.UserRepository;
import com.crazyspace_edu.api.request.AiContentRequest;
import com.crazyspace_edu.api.response.AiContentResponse;
import com.crazyspace_edu.api.response.ConversationListResponse;
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
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    @PostMapping("/conversations")
    public ResponseEntity<Long> startNewConversation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        Conversation conversation = Conversation.builder()
                .user(user)
                .build();

        conversationRepository.save(conversation);
        return ResponseEntity.ok(conversation.getId());
    }

    @PostMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<AiContentResponse> addMessageToConversation(@PathVariable Long conversationId, @RequestBody AiContentRequest aiContentRequest) {

        AiContentResponse message = openAIService.callOpenAiService(conversationId, aiContentRequest);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow();
        return ResponseEntity.ok(conversation.getMessages());
    }

    @GetMapping("/conversations/my/list")
    public ResponseEntity<List<ConversationListResponse>> getMyList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        Long userId = user.getId();
        List<Long> ids = openAIService.getConversationIds(userId);
        List<ConversationListResponse> responses = openAIService.conversationList(ids);
        return ResponseEntity.ok(responses);
    }

}
