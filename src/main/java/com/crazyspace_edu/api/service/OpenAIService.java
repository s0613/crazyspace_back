package com.crazyspace_edu.api.service;

import com.crazyspace_edu.api.domain.ChatMessage;
import com.crazyspace_edu.api.domain.user.User;
import com.crazyspace_edu.api.repository.ChatMessageRepository;
import com.crazyspace_edu.api.repository.UserRepository;
import com.crazyspace_edu.api.request.AiContentRequest;
import com.crazyspace_edu.api.response.AiContentResponse;
import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final ConversationalChain conversationalChain;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ChatLanguageModel chatLanguageModel;
    private final ChatMemory chatMemory;

    public AiContentResponse callOpenAiService(AiContentRequest createRequest){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

//            String answer = conversationalChain.execute(createRequest.getContent());
            Assistant assistant = AiServices.builder(Assistant.class)
                    .chatLanguageModel(chatLanguageModel)
                    .chatMemory(chatMemory)
                    .build();

            String answer = assistant.chat(createRequest.getContent());
            // Save user message
            ChatMessage userMessage = ChatMessage.builder()
                    .user(user)
                    .role("USER")
                    .content(createRequest.getContent())
                    .build();
            chatMessageRepository.save(userMessage);

            // Save AI response
            ChatMessage aiMessage = ChatMessage.builder()
                    .user(user)
                    .role("AI")
                    .content(answer)
                    .build();
            chatMessageRepository.save(aiMessage);

            return new AiContentResponse(answer);
        } catch (Exception e) {
            return new AiContentResponse("Error");
        }
    }
}
