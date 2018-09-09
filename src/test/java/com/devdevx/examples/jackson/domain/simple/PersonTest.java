package com.devdevx.examples.jackson.domain.simple;

import com.devdevx.examples.jackson.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonTest extends JsonTest {

    @Test
    @DisplayName("Simple conversion object to json")
    public void objectToJson() throws JsonProcessingException {
        Person object = new Person("John Doe");
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("John Doe", doc.read("$.name", String.class));
    }

    @Test
    @DisplayName("Simple conversion json to object")
    public void jsonToObject() throws IOException {
        String json = "{\"name\":\"John Doe\"}";
        Person object = toObject(json, Person.class);
        assertEquals("John Doe", object.getName());
    }
}
