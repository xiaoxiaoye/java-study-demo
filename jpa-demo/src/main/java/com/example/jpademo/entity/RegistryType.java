package com.example.jpademo.entity;

public enum RegistryType {
    HARBOR("harbor"),
    V2("v2");

    private final String name;

    private RegistryType(String name) {
        this.name = name;
    }

    public String getRegistryType() {
        return name;
    }
}
