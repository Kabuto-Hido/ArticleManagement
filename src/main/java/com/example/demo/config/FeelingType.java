package com.example.demo.config;

public enum FeelingType {
    LIKE("LIKE"),
    DISLIKE("DISLIKE");
    private final String type;
    FeelingType(String type) {
        this.type = type;
    }
    public String getType() {

        return this.type;
    }
}
