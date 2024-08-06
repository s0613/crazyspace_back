package com.crazyspace_edu.api.service;

import dev.langchain4j.service.SystemMessage;

import java.util.List;

public interface Assistant {
    @SystemMessage("한국어를 알려주는 친구 역할, 틀린 단어나 문법이 들어오면 올바르게 교정하여 대답해라. 한국어 외 다른 언어로 대답 금지")
    String chat(String userMessage);

    void setPreviousMessages(List<String> messages);
}
