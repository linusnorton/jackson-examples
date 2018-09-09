package com.devdevx.examples.jackson.domain.cyclic;

import com.devdevx.examples.jackson.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest extends JsonTest {

    @Test
    @DisplayName("Cyclic relation conversion object to json")
    public void objectToJson() throws JsonProcessingException {
        User object = new User("John Doe", new User.ContactData("555 555 555"));
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("John Doe", doc.read("$.name", String.class));
        assertEquals("555 555 555", doc.read("$.contactData.phone", String.class));
        assertEquals(null, doc.read("$.contactData.user", Object.class));
    }

    @Test
    @DisplayName("Cyclic relation conversion object to json by child element")
    public void objectToJsonChild() throws JsonProcessingException {
        User object = new User("John Doe", new User.ContactData("555 555 555"));
        String json = toJson(object.getContactData());
        DocumentContext doc = toDoc(json);
        assertEquals("555 555 555", doc.read("$.phone", String.class));
        assertEquals("John Doe", doc.read("$.user.name", String.class));
        assertEquals(null, doc.read("$.user.contactData", Object.class));
    }

    @Test
    @DisplayName("Cyclic relation conversion json to object")
    public void jsonToObject() throws IOException {
        String json = "{\"name\":\"John Doe\",\"contactData\":{\"phone\":\"555 555 555\"}}";
        User object = toObject(json, User.class);
        assertEquals("John Doe", object.getName());
        assertEquals("555 555 555", object.getContactData().getPhone());
        assertEquals(null, object.getContactData().getUser());
    }

    @Test
    @DisplayName("Cyclic relation conversion json to object by child element")
    public void jsonToObjectChild() throws IOException {
        String json = "{\"phone\":\"555 555 555\",\"user\":{\"name\":\"John Doe\"}}";
        User.ContactData object = toObject(json, User.ContactData.class);
        assertEquals("555 555 555", object.getPhone());
        assertEquals("John Doe", object.getUser().getName());
        assertEquals(null, object.getUser().getContactData());
    }
}
