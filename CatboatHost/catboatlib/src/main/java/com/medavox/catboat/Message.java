package com.medavox.catboat;

public class Message {
    private String text;
    private long receivedTime;
    private ChatPartner sender;
    public Message(String text, ChatPartner sender) {
        receivedTime = System.currentTimeMillis();
        this.text = text;
        this.sender = sender;
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
}
