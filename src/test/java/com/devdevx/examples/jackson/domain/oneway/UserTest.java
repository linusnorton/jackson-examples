package com.devdevx.examples.jackson.domain.oneway;

import com.devdevx.examples.jackson.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest extends JsonTest {

    @Test
    @DisplayName("One way fields conversion object to json")
    public void objectToJson() throws JsonProcessingException {
        User object = new User("John Doe", "secret", 100);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("John Doe", doc.read("$.name", String.class));
        assertEquals(Integer.valueOf(100), doc.read("$.identifier", Integer.class));
        assertEquals(null, doc.read("$.password", Integer.class));
    }

    @Test
    @DisplayName("One way field conversion json to object")
    public void jsonToObject() throws IOException {
        String json = "{\"name\":\"John Doe\",\"identifier\":100,\"password\":\"secret\"}";
        User object = toObject(json, User.class);
        assertEquals("John Doe", object.getName());
        assertEquals(null, object.getIdentifier());
        assertEquals("secret", object.getPassword());
    }
}
