package com.crazyspace_edu.api.service;

import com.crazyspace_edu.api.domain.ai.ChatMessage;
import com.crazyspace_edu.api.domain.ai.Conversation;
import com.crazyspace_edu.api.repository.ChatMessageRepository;
import com.crazyspace_edu.api.repository.conversation.ConversationRepositoryRepository;
import com.crazyspace_edu.api.repository.UserRepository;
import com.crazyspace_edu.api.request.AiContentRequest;
import com.crazyspace_edu.api.response.AiContentResponse;
import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpenAIService {


    private final ChatMessageRepository chatMessageRepository;
    private final ChatLanguageModel chatLanguageModel;
    private final ChatMemory chatMemory;
    private final ConversationRepositoryRepository conversationRepository;
    private static final int MAX_TOKEN_LIMIT = 4096;

    public AiContentResponse callOpenAiService(Long conversationId, AiContentRequest createRequest) {
        try {
            Conversation conversation = conversationRepository.findById(conversationId)
                    .orElseThrow(() -> new IllegalArgumentException("Conversation not found with ID: " + conversationId));

            // 이전 메시지를 가져와서 대화 이력에 추가
            List<ChatMessage> previousMessages = conversation.getMessages();
            List<String> dialogueHistory = new ArrayList<>();

            for (ChatMessage message : previousMessages) {
                dialogueHistory.add(message.getRole() + ": " + message.getContent());
            }

            // 새로운 메시지를 대화 이력에 추가
            String userMessage = createRequest.getContent();
            dialogueHistory.add("user: " + userMessage);

            // 대화 이력의 토큰 수가 너무 많아지면 오래된 메시지를 제거
            while (getTokenCount(dialogueHistory) > MAX_TOKEN_LIMIT) {
                dialogueHistory.remove(0);
            }

            Assistant assistant = AiServices.builder(Assistant.class)
                    .chatLanguageModel(chatLanguageModel)
                    .chatMemory(chatMemory)
                    .build();

            String aiResponse = assistant.chat(String.join("\n", dialogueHistory));
            dialogueHistory.add("ai: " + aiResponse);

            // 대화 메시지 저장
            saveChatMessage(conversation, "user", userMessage);
            saveChatMessage(conversation, "ai", aiResponse);

            return new AiContentResponse(aiResponse);
        } catch (Exception e) {
            return new AiContentResponse("Error");
        }
    }

    private int getTokenCount(List<String> messages) {
        return messages.stream().mapToInt(String::length).sum(); // 토큰 수 계산 방법을 좀 더 정확히 조정할 수 있음
    }

    private void saveChatMessage(Conversation conversation, String role, String content) {
        ChatMessage chatMessage = ChatMessage.builder()
                .conversation(conversation)
                .role(role)
                .content(content)
                .build();
        chatMessageRepository.save(chatMessage);
    }

}
