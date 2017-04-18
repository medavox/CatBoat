package com.medavox.catboat;

public class ChatPartner {
    private String name;
    private byte trustLevel;
    private long knownSince;

    public ChatPartner(String name) {
        knownSince = System.currentTimeMillis();
        trustLevel = 0;
        this.name = name;
    }
}
