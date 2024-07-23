package org.crazyspace_edu.api.service;

import org.crazyspace_edu.api.domain.GeneratedAiContent;
import org.crazyspace_edu.api.domain.user.User;
import org.crazyspace_edu.api.exception.ResourceNotFoundException;
import org.crazyspace_edu.api.repository.GeneratedAiContentRepository;
import org.crazyspace_edu.api.repository.UserRepository;
import org.crazyspace_edu.api.request.AiContentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneratedAiContentService {
    private static final Logger log = LoggerFactory.getLogger(GeneratedAiContentService.class);
    private final GeneratedAiContentRepository contentRepository;
    private final UserRepository userRepository;

    @Autowired
    public GeneratedAiContentService(GeneratedAiContentRepository contentRepository, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }

    public GeneratedAiContent createContent(Long userId, AiContentRequest content) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

            GeneratedAiContent generatedAiContent = GeneratedAiContent.builder()
                    .title(content.getContent().substring(0, 10))
                    .content(content.getContent())
                    .user(user)  // User 정보를 설정
                    .build();

            contentRepository.save(generatedAiContent);
            return generatedAiContent;
        } catch (Exception e) {
            log.error("Error creating content: {}", e.getMessage(), e);
            throw e;
        }
    }
}

