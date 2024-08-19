package com.crazyspace_edu.api.service;

import com.crazyspace_edu.api.domain.GeneratedAiContent;
import com.crazyspace_edu.api.domain.user.User;
import com.crazyspace_edu.api.exception.ResourceNotFoundException;
import com.crazyspace_edu.api.repository.GeneratedAiContentRepository;
import com.crazyspace_edu.api.request.AiContentRequest;
import com.crazyspace_edu.api.repository.UserRepository;
import com.crazyspace_edu.api.response.ContentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneratedAiContentService {
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

    public List<ContentDTO> getTop5GeneratedContents() {
        List<GeneratedAiContent> contents = contentRepository.findTop5ByOrderByChgDtDesc();
        return contents.stream()
                .map(content -> new ContentDTO(
                        content.getTitle(),
                        content.getContent()))
                .collect(Collectors.toList());

    }
}

