package com.crazyspace_edu.api.service;

import java.util.ArrayList;
import java.util.List;

public class AssistantImpl implements Assistant{
    private List<String> previousMessages = new ArrayList<>();

    @Override
    public String chat(String userMessage) {
        // 기존 메시지에 새로운 사용자 메시지 추가
        previousMessages.add("user: " + userMessage);

        // AI 응답 생성 (여기서는 단순히 이전 메시지 수를 응답으로 반환하는 예제)
        String aiResponse = "AI 응답 예제"; // 실제로는 AI 서비스 호출 필요
        previousMessages.add("ai: " + aiResponse);

        return aiResponse;
    }

    @Override
    public void setPreviousMessages(List<String> messages) {
        this.previousMessages = messages;
    }
}
