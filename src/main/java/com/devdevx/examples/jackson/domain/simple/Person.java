package com.devdevx.examples.jackson.domain.simple;

public class Person {
    private String name;

    private Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
