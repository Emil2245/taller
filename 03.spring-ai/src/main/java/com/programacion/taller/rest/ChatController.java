package com.programacion.taller.rest;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
      chatClient = builder.build();
    }


    @GetMapping("/chat")
    public String chat (@RequestParam String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }


}
