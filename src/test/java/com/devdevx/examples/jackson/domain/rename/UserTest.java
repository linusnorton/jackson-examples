package com.devdevx.examples.jackson.domain.rename;

import com.devdevx.examples.jackson.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest extends JsonTest {

    @Test
    @DisplayName("Rename fields conversion object to json")
    public void objectToJson() throws JsonProcessingException {
        User object = new User("John Doe", "external", "user", "code");
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("John Doe", doc.read("$.name", String.class));
        assertEquals("external", doc.read("$.externalCode", String.class));
        assertEquals("user", doc.read("$.publicCode", String.class));
        assertEquals("code", doc.read("$.internalCode", String.class));
    }

    @Test
    @DisplayName("Rename fields conversion json to object")
    public void jsonToObject() throws IOException {
        String json = "{\"name\":\"John Doe\",\"code\":\"external\",\"userCode\":\"user\",\"internalCode\":\"code\"}";
        User object = toObject(json, User.class);
        assertEquals("John Doe", object.getName());
        assertEquals("external", object.getExternalCode());
        assertEquals("user", object.getUserCode());
        assertEquals("code", object.getCode());
    }
}
