package com.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chat.dto.ChatRequest;
import com.chat.dto.ChatResponse;
import com.chat.dto.Message;

import java.util.Arrays;

@Service
public class ChatService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url}")
    private String url;

    public ChatResponse sendChatRequest() {

        // Create message objects
        Message message1 = new Message();
        message1.setRole("system");
        message1.setContent("Always answer in rhymes. Today is Thursday");

        Message message2 = new Message();
        message2.setRole("user");
        message2.setContent("What day is it today?");

        Message message3 = new Message();
        message3.setRole("assistant");
        message3.setContent("Today is Thursday, that’s the clue,\nThe day we get things done, it's true!");

        Message message4 = new Message();
        message4.setRole("user");
        message4.setContent("what is the day today?");

        // Create ChatRequest object
        ChatRequest request = new ChatRequest();
        request.setModel("tinyllama");
        request.setMessages(Arrays.asList(message1, message2, message3, message4));
        request.setTemperature(0.7);
        request.setMax_tokens(100);
        request.setStream(false);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTP entity with body and headers
        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        // Send POST request and map response to ChatResponse
        ResponseEntity<ChatResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, ChatResponse.class);

        return response.getBody();
    }
}
