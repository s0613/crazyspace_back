package com.crazyspace_edu.api.service;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {
    @SystemMessage
    String chat(String userMessage);
}
