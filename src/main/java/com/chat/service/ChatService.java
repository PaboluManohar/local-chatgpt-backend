package com.chat.service;

import com.chat.dto.ChatRequest;
import com.chat.dto.ChatResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

@Service
public class ChatService {

    private final RestTemplate restTemplate;

    @Value("${url}")  // URL to your backend service
    private String url;

    public ChatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ChatResponse sendChatRequest(ChatRequest request) {
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTP entity with body and headers
        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        // Send the POST request
        ResponseEntity<ChatResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, ChatResponse.class);

        return response.getBody();
    }
}
