package com.chat.chatapp.controller;


import com.chat.chatapp.model.ChatMessage;
import com.chat.chatapp.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage){
       chatMessage.setTimestamp(LocalDateTime.now());
       chatMessageRepository.save(chatMessage);
        return chatMessage;
    }
}
