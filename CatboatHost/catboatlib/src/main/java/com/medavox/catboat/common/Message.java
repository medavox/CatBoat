package com.medavox.catboat.common;

import com.medavox.catboat.ChatPartner;

public class Message {
    private String text;
    private long receivedTime;
    private ChatPartner sender;
    private long id;

    //todo:make this persistent
    private static long messageIdCounter = 0;
    public Message(String text, ChatPartner sender) {
        receivedTime = System.currentTimeMillis();
        this.text = text;
        this.sender = sender;
        this.id = messageIdCounter;
        //todo: make this thread-safe
        messageIdCounter++;
    }

    public String getText() {
        return text;
    }

    public long getTimeReceived() {
        return receivedTime;
    }
    public ChatPartner getChatPartner() {
        return sender;
    }

    public long getId() {
        return id;
    }
}
