package org.crazyspace_edu.api.service;

import org.crazyspace_edu.api.domain.GeneratedAiContent;
import org.crazyspace_edu.api.domain.user.User;
import org.crazyspace_edu.api.exception.ResourceNotFoundException;
import org.crazyspace_edu.api.repository.GeneratedAiContentRepository;
import org.crazyspace_edu.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneratedAiContentService {
    private final GeneratedAiContentRepository contentRepository;
    private final UserRepository userRepository;

    @Autowired
    public GeneratedAiContentService(GeneratedAiContentRepository contentRepository, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }

    public GeneratedAiContent createContent(Long userId, GeneratedAiContent content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        content.setUser(user);
        return contentRepository.save(content);
    }
}

