package com.crazyspace_edu.api.controller;


import com.crazyspace_edu.api.request.AiContentRequest;
import com.crazyspace_edu.api.response.AiContentResponse;
import com.crazyspace_edu.api.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenAIController {
    private final OpenAIService openAIService;
    @PostMapping(value = "/generate-text", produces = "application/json;charset=UTF-8")
    public ResponseEntity<AiContentResponse> chatAi(@RequestBody AiContentRequest createRequest){
        AiContentResponse response = openAIService.callOpenAiService(createRequest);
        if (response.getMessage() == null) {
            response = new AiContentResponse("please again");
        }
        return ResponseEntity.ok(response);
    }
}
