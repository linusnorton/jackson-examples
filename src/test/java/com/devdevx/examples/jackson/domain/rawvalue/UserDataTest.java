package com.devdevx.examples.jackson.domain.rawvalue;

import com.devdevx.examples.jackson.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserDataTest extends JsonTest {

    @Test
    @DisplayName("Raw value conversion object to json")
    public void objectToJson() throws JsonProcessingException {
        UserData object = new UserData("John Doe", "{\"data\":\"json\"}");
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("John Doe", doc.read("$.name", String.class));
        assertEquals("json", doc.read("$.json.data", String.class));
    }

    @Test
    @DisplayName("Raw value conversion json to object")
    public void jsonToObject() throws IOException {
        String json = "{\"name\":\"John Doe\",\"json\":{\"data\":\"json\"}}";
        UserData object = toObject(json, UserData.class);
        assertEquals("John Doe", object.getName());
        assertEquals("{\"data\":\"json\"}", object.getJson());
    }
}
