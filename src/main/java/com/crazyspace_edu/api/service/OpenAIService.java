package com.crazyspace_edu.api.service;

import com.crazyspace_edu.api.request.AiContentRequest;
import com.crazyspace_edu.api.response.AiContentResponse;
import dev.langchain4j.chain.ConversationalChain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final ConversationalChain conversationalChain;

    public AiContentResponse callOpenAiService(AiContentRequest createRequest){
        try {
            String answer = conversationalChain.execute(createRequest.getContent());
            return new AiContentResponse(answer);
        } catch (Exception e) {
            return new AiContentResponse("Error");
        }
    }
}
