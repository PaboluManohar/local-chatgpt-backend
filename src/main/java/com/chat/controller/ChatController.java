//package com.chat.controller;
//
//import com.chat.dto.ChatRequest;
//import com.chat.dto.ChatResponse;
//import com.chat.dto.Message;
//import com.chat.helper.UserPrompt;
//import com.chat.service.ChatService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//@RestController
//@RequestMapping("/api/chat")  // You can define your base path here
//public class ChatController {
//
//    @Autowired
//    private ChatService chatService;
//
//    @PostMapping("/send-chat")
//    public ChatResponse sendChat(@RequestBody String jsonInput)throws IOException {
//    	
//    	
//        UserPrompt userPrompt;
//		try {
//			userPrompt = ObjectMapper.readValue(jsonInput, UserPrompt.class);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//    	
//    	System.out.println("_______________________________________________");
//    	
//    	System.out.println(userPrompt);
//    	
//    	System.out.println("_______________________________________________");
//  	
//        // Construct the chat request based on the user's prompt
//        Message message1 = new Message();
//        message1.setRole("system");
//        message1.setContent("Always answer in rhymes. Today is Thursday");
//
//        Message message2 = new Message();
//        message2.setRole("user");
//        message2.setContent(userPrompt);
//
//        Message message3 = new Message();
//        message3.setRole("assistant");
//        message3.setContent("Today is Thursday, that’s the clue,\nThe day we get things done, it's true!");
//
//        Message message4 = new Message();
//        message4.setRole("user");
//        message4.setContent("what is the day today?");
//
//        // Create ChatRequest object
//        ChatRequest request = new ChatRequest();
//        request.setModel("tinyllama");
//        request.setMessages(Arrays.asList(message1, message2, message3, message4));
//        request.setTemperature(0.7);
//        request.setMax_tokens(100);
//        request.setStream(false);
//
//        // Send the request to the service and get the response
//        return chatService.sendChatRequest(request);
//    }
//}
package com.chat.controller;

import com.chat.dto.ChatRequest;
import com.chat.dto.ChatResponse;
import com.chat.dto.Message;
import com.chat.helper.UserPrompt;
import com.chat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;  // Jackson ObjectMapper
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ObjectMapper objectMapper;  // Jackson's ObjectMapper for JSON conversion

    @PostMapping("/send-chat")
    public ChatResponse sendChat(@RequestBody String jsonInput) throws IOException {
        // Deserialize the JSON string to the UserPrompt object
        UserPrompt userPrompt = objectMapper.readValue(jsonInput, UserPrompt.class);

        String userPromptText = userPrompt.getUserPrompt();  // Extracted value from JSON

        List<Message> messages = new ArrayList<>();

        // Static message from system (could be dynamic too if needed)
        Message systemMessage = new Message();
        systemMessage.setRole("system");
        systemMessage.setContent("act as a personal ai assistant");
        messages.add(systemMessage);

        // User's dynamic prompt
        Message userMessage = new Message();
        userMessage.setRole("user");
        userMessage.setContent(userPromptText);  // Use the dynamically received prompt
        messages.add(userMessage);

  

        // Create ChatRequest object with dynamic message list
        ChatRequest request = new ChatRequest();
        request.setModel("tinyllama");
        request.setMessages(messages);
        request.setTemperature(0.7);
        request.setMax_tokens(100);
        request.setStream(false);

        // Send the request to the service and get the response
        return chatService.sendChatRequest(request);
    }
}
