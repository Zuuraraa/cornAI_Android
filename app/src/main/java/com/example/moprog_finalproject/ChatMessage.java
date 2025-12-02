package com.example.moprog_finalproject;

public class ChatMessage {
    private String message;
    private boolean isUser; //Kalo true berarti user, kalo false berarti bot

    public ChatMessage(String message, boolean isUser) {
        this.message = message;
        this.isUser = isUser;
    }

    public String getMessage() {
        return message;
    }

    public boolean isUser() {
        return isUser;
    }
}
