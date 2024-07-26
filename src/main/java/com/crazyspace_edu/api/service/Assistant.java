package com.crazyspace_edu.api.service;

public interface Assistant {
    @SystemMessage
    String chat(String userMessage);
}
