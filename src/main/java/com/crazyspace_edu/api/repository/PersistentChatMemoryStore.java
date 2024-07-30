package com.crazyspace_edu.api.repository;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersistentChatMemoryStore implements ChatMemoryStore {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @Autowired
    public PersistentChatMemoryStore(ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        Long userId = (Long) memoryId;
        return chatMessageRepository.findByUserId(userId);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        for (ChatMessage message : messages) {
            chatMessageRepository.save(message);
        }
    }

    @Override
    public void deleteMessages(Object memoryId) {
        Long userId = (Long) memoryId;
        List<ChatMessage> messages = chatMessageRepository.findByUserId(userId);
        chatMessageRepository.deleteAll(messages);
    }
}
