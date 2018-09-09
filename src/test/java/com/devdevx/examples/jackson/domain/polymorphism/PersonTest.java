package com.devdevx.examples.jackson.domain.polymorphism;

import com.devdevx.examples.jackson.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class PersonTest extends JsonTest {

    @Test
    @DisplayName("Polymorphism conversion person to json")
    public void objectToJson() throws JsonProcessingException {
        Person object = new Person("John Doe");
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("person", doc.read("$.type", String.class));
        assertEquals("John Doe", doc.read("$.name", String.class));
    }

    @Test
    @DisplayName("Polymorphism conversion student to json")
    public void objectToJson1() throws JsonProcessingException {
        Person.Student object = new Person.Student("John Doe", "MIT");
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("student", doc.read("$.type", String.class));
        assertEquals("John Doe", doc.read("$.name", String.class));
        assertEquals("MIT", doc.read("$.university", String.class));
    }

    @Test
    @DisplayName("Polymorphism conversion employee to json")
    public void objectToJson2() throws JsonProcessingException {
        Person.Employee object = new Person.Employee("John Doe", "Google");
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("employee", doc.read("$.type", String.class));
        assertEquals("John Doe", doc.read("$.name", String.class));
        assertEquals("Google", doc.read("$.company", String.class));
    }

    @Test
    @DisplayName("Polymorphism conversion exchange student to json")
    public void objectToJson3() throws JsonProcessingException {
        Person.ExchangeStudent object = new Person.ExchangeStudent("John Doe", "MIT", "Spain");
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("exchangeStudent", doc.read("$.type", String.class));
        assertEquals("John Doe", doc.read("$.name", String.class));
        assertEquals("MIT", doc.read("$.university", String.class));
        assertEquals("Spain", doc.read("$.country", String.class));
    }

    @Test
    @DisplayName("Polymorphism conversion exchange json to exchange student")
    public void jsonToObject() throws IOException {
        String json = "{\"type\":\"exchangeStudent\",\"name\":\"John Doe\",\"university\":\"MIT\",\"country\":\"Spain\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Person object = objectMapper.readValue(json, Person.class);
        assertEquals("John Doe", object.getName());
        assertFalse(object instanceof Person.Employee);
        assertTrue(object instanceof Person.Student);
        assertTrue(object instanceof Person.ExchangeStudent);
        Person.ExchangeStudent finalObject = (Person.ExchangeStudent) object;
        assertEquals("MIT", finalObject.getUniversity());
        assertEquals("Spain", finalObject.getCountry());
    }

}
