package com.crazyspace_edu.api.controller;


import com.crazyspace_edu.api.domain.ai.ChatMessage;
import com.crazyspace_edu.api.domain.ai.Conversation;
import com.crazyspace_edu.api.domain.user.User;
import com.crazyspace_edu.api.repository.ChatMessageRepository;
import com.crazyspace_edu.api.repository.conversation.ConversationRepositoryCustom;
import com.crazyspace_edu.api.repository.conversation.ConversationRepositoryRepository;
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
    private final ConversationRepositoryRepository conversationRepository;
    private final UserRepository userRepository;

//    @PostMapping("/converse/ai")
//    public ResponseEntity<AiContentResponse> createContent(@RequestBody AiContentRequest aiContentRequest) {
//        AiContentResponse response = openAIService.callOpenAiService(aiContentRequest);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/chat/messages")
//    public ResponseEntity<List<ChatMessage>> getMessages() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
//
//        List<ChatMessage> messages = chatMessageRepository.findByUserId(user.getId());
//        return ResponseEntity.ok(messages);
//    }
    @PostMapping("/conversations")
    public ResponseEntity<String> startNewConversation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        Conversation conversation = Conversation.builder()
                .user(user)
                .build();

        conversationRepository.save(conversation);
        return ResponseEntity.ok("Good");
    }

    @PostMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<AiContentResponse> addMessageToConversation(@PathVariable Long conversationId, @RequestBody AiContentRequest aiContentRequest) {

        AiContentResponse response = openAIService.callOpenAiService(conversationId, aiContentRequest);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow();
        return ResponseEntity.ok(conversation.getMessages());
    }
}
