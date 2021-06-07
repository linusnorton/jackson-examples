package com.devdevx.examples.jackson.domain.flat;

import com.devdevx.examples.jackson.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonTest extends JsonTest {

    @Test
    @DisplayName("Flat json conversion object to json")
    public void objectToJson() throws IOException {
        Person object = new Person("John Doe", new Person.Address("Lollipop Street", 101, new Person.Address.Goat("Derp")), new Person.Address(
            "Soda Street", 111, new Person.Address.Goat("Derp2")));
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("John Doe", doc.read("$.name", String.class));
        assertEquals("Lollipop Street", doc.read("$.mainAddressstreet", String.class));
        assertEquals(Integer.valueOf(101), doc.read("$.mainAddressnumber", Integer.class));
        assertEquals("Soda Street", doc.read("$.secondAddressstreet", String.class));
        assertEquals(Integer.valueOf(111), doc.read("$.secondAddressnumber", Integer.class));

        // this is fine
        assertEquals("Derp2", doc.read("$.secondAddressmyGoat.value", String.class));

        Person person = toObject(json, Person.class);

        // this fails, value == null
        assertEquals("Derp", person.getSecondAddress().getMyGoat().getValue());
    }

    @Test
    @DisplayName("Flat json conversion json to object")
    public void jsonToObject() throws IOException {
        String json = "{\"name\":\"John Doe\",\"mainAddressstreet\":\"Lollipop Street\",\"mainAddressnumber\":101,\"secondAddressstreet\":\"Soda " +
            "Street\", \"secondAddressmyGoat\": { \"value\": \"Derp\" }, \"secondAddressnumber\":111}";
        Person object = toObject(json, Person.class);
        assertEquals("John Doe", object.getName());
        assertEquals("Lollipop Street", object.getMainAddress().getStreet());
        assertEquals(Integer.valueOf(101), object.getMainAddress().getNumber());
        assertEquals("Soda Street", object.getSecondAddress().getStreet());
        assertEquals(Integer.valueOf(111), object.getSecondAddress().getNumber());

        // this fails, value is null
        assertEquals("Derp", object.getSecondAddress().getMyGoat().getValue());

    }
}
