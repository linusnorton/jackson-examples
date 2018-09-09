package com.devdevx.examples.jackson.domain.root;

import com.devdevx.examples.jackson.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.DocumentContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest extends JsonTest {

    @Test
    @DisplayName("Root conversion object to json")
    public void objectToJson() throws JsonProcessingException {
        User object = new User("John Doe");
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.WRAP_ROOT_VALUE);
        String json = objectMapper.writeValueAsString(object);
        DocumentContext doc = toDoc(json);
        assertEquals("John Doe", doc.read("$.user.name", String.class));
    }

    @Test
    @DisplayName("Root conversion json to object")
    public void jsonToObject() throws IOException {
        String json = "{\"user\":{\"name\":\"John Doe\"}}";
        ObjectMapper objectMapper = new ObjectMapper().enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        User object = objectMapper.readValue(json, User.class);
        assertEquals("John Doe", object.getName());
    }
}
