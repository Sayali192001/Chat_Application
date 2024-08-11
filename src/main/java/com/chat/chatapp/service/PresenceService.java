package com.chat.chatapp.service;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PresenceService {
    private ConcurrentHashMap<String, Boolean> onlineUsers = new ConcurrentHashMap<>();

    public void userLoggedIn(String username){
        onlineUsers.put(username,true);
    }

    public void userLoggedOut(String username){
        onlineUsers.remove(username);
    }

    public Set<String> getOnlineUsers(){
        return onlineUsers.keySet();
    }
}
