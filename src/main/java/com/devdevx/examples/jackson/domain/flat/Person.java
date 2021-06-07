package com.devdevx.examples.jackson.domain.flat;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Person {

    private Person() {
    }

    public Person(String name, Address mainAddress, Address secondAddress) {
        this.name = name;
        this.mainAddress = mainAddress;
        this.secondAddress = secondAddress;
    }

    private String name;
    @JsonUnwrapped(prefix = "mainAddress")
    private Address mainAddress;
    @JsonUnwrapped(prefix = "secondAddress")
    private Address secondAddress;

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Address getMainAddress() {
        return mainAddress;
    }

    private void setMainAddress(Address mainAddress) {
        this.mainAddress = mainAddress;
    }

    public Address getSecondAddress() {
        return secondAddress;
    }

    private void setSecondAddress(Address secondAddress) {
        this.secondAddress = secondAddress;
    }

    public static class Address {
        public String street;
        private Integer number;
        public Goat myGoat;

        private Address() {
        }

        public Address(String street, Integer number, Goat myGoat) {
            this.street = street;
            this.number = number;
            this.myGoat = myGoat;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public Goat getMyGoat() {
            return myGoat;
        }

        public void setMyGoat(Goat myGoat) {
            this.myGoat = myGoat;
        }

        public static class Goat {
            public String value;

            Goat() {

            }

            @JsonCreator
            Goat(@JsonProperty("value") String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
