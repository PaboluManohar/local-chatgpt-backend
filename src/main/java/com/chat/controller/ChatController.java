package com.chat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.dto.ChatResponse;
import com.chat.service.ChatService;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/send-chat")
    public ChatResponse sendChat() {
        return chatService.sendChatRequest();
    }
}
