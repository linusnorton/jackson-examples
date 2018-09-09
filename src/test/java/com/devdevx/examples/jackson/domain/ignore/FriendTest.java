package com.devdevx.examples.jackson.domain.ignore;

import com.devdevx.examples.jackson.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FriendTest extends JsonTest {

    @Test
    @DisplayName("Ignore field conversion object to json")
    public void objectToJson() throws JsonProcessingException {
        Friend object = new Friend("John Doe", "secret");
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("John Doe", doc.read("$.name", String.class));
        assertEquals(null, doc.read("$.secret", String.class));
    }

    @Test
    @DisplayName("Ignore field conversion json to object")
    public void jsonToObject() throws IOException {
        String json = "{\"name\":\"John Doe\",\"secret\":\"secret\"}";
        Friend object = toObject(json, Friend.class);
        assertEquals("John Doe", object.getName());
        assertEquals(null, object.getSecret());
    }
}
