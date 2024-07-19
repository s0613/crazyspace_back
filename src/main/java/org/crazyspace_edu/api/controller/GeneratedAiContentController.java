package org.crazyspace_edu.api.controller;

import org.crazyspace_edu.api.domain.GeneratedAiContent;
import org.crazyspace_edu.api.service.GeneratedAiContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/generatedAiContents")
public class GeneratedAiContentController {
    private final GeneratedAiContentService contentService;

    @Autowired
    public GeneratedAiContentController(GeneratedAiContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<GeneratedAiContent> createContent(@PathVariable Long userId, @RequestBody GeneratedAiContent content) {
        GeneratedAiContent createdContent = contentService.createContent(userId, content);
        return new ResponseEntity<>(createdContent, HttpStatus.CREATED);
    }
}
