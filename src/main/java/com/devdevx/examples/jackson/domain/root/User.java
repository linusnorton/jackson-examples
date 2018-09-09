package com.devdevx.examples.jackson.domain.root;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("user")
public class User {
    private String name;

    private User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
