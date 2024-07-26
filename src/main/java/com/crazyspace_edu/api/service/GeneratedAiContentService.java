package com.crazyspace_edu.api.service;

import com.crazyspace_edu.api.domain.GeneratedAiContent;
import com.crazyspace_edu.api.domain.user.User;
import com.crazyspace_edu.api.exception.ResourceNotFoundException;
import com.crazyspace_edu.api.repository.GeneratedAiContentRepository;
import com.crazyspace_edu.api.request.AiContentRequest;
import com.crazyspace_edu.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String contentText = content.getContent();
        String title = contentText.length() > 10 ? contentText.substring(0, 10) : contentText;

        GeneratedAiContent generatedAiContent = GeneratedAiContent.builder()
                .title(title)
                .content(contentText)
                .user(user)
                .build();
        contentRepository.save(generatedAiContent);
        return generatedAiContent;
    }

    public List<GeneratedAiContent> getTop5GeneratedContents() {
        return contentRepository.findTop5ByOrderByChgDtDesc();
    }
}
