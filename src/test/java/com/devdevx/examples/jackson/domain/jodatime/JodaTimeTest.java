package com.devdevx.examples.jackson.domain.jodatime;

import com.devdevx.examples.jackson.JsonTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import org.joda.time.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JodaTimeTest extends JsonTest {

    private static final DateTime DATE_TIME = new DateTime(2018, 1, 1, 14, 22, 33, 25, DateTimeZone.UTC);
    private static final Instant INSTANT = DATE_TIME.toInstant();
    private static final YearMonth YEAR_MONTH = new YearMonth(2018, 1);
    private static final MonthDay MONTH_DAY = new MonthDay(1, 14);
    private static final LocalDateTime LOCAL_DATE_TIME = new LocalDateTime(2018, 1, 1, 14, 22, 33, 25);
    private static final LocalDate LOCAL_DATE = LOCAL_DATE_TIME.toLocalDate();
    private static final LocalTime LOCAL_TIME = LOCAL_DATE_TIME.toLocalTime();

    @Test
    @DisplayName("DateTime conversion object to json")
    public void dateTimeObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setIsoDateTime(DATE_TIME);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("2018-01-01T14:22:33.025Z", doc.read("$.isoDateTime", String.class));
    }

    @Test
    @DisplayName("DateTime conversion json to object")
    public void dateTimeJsonToObject() throws IOException {
        String json = "{\"isoDateTime\":\"2018-01-01T14:22:33.025Z\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(DATE_TIME, object.getIsoDateTime());
    }

    @Test
    @DisplayName("Instant conversion object to json")
    public void instantObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setIsoInstant(INSTANT);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("2018-01-01T14:22:33.025Z", doc.read("$.isoInstant", String.class));
    }

    @Test
    @DisplayName("Instant conversion json to object")
    public void instantJsonToObject() throws IOException {
        String json = "{\"isoInstant\":\"2018-01-01T14:22:33.025Z\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(INSTANT, object.getIsoInstant());
    }

    @Test
    @DisplayName("YearMonth conversion object to json")
    public void yearMonthObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setIsoYearMonth(YEAR_MONTH);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("2018-01", doc.read("$.isoYearMonth", String.class));
    }

    @Test
    @DisplayName("YearMonth conversion json to object")
    public void yearMonthJsonToObject() throws IOException {
        String json = "{\"isoYearMonth\":\"2018-01\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(YEAR_MONTH, object.getIsoYearMonth());
    }

    @Test
    @DisplayName("MonthDay conversion object to json")
    public void monthDayObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setIsoMonthDay(MONTH_DAY);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("--01-14", doc.read("$.isoMonthDay", String.class));
    }

    @Test
    @DisplayName("MonthDay conversion json to object")
    public void monthDayJsonToObject() throws IOException {
        String json = "{\"isoMonthDay\":\"--01-14\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(MONTH_DAY, object.getIsoMonthDay());
    }

    @Test
    @DisplayName("LocalDateTime conversion object to json")
    public void localDateTimeObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setIsoLocalDateTime(LOCAL_DATE_TIME);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("2018-01-01T14:22:33.025", doc.read("$.isoLocalDateTime", String.class));
    }

    @Test
    @DisplayName("LocalDateTime conversion json to object")
    public void localDateTimeJsonToObject() throws IOException {
        String json = "{\"isoLocalDateTime\":\"2018-01-01T14:22:33.025\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(LOCAL_DATE_TIME, object.getIsoLocalDateTime());
    }

    @Test
    @DisplayName("LocalDate conversion object to json")
    public void localDateObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setIsoLocalDate(LOCAL_DATE);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("2018-01-01", doc.read("$.isoLocalDate", String.class));
    }

    @Test
    @DisplayName("LocalDate conversion json to object")
    public void localDateJsonToObject() throws IOException {
        String json = "{\"isoLocalDate\":\"2018-01-01\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(LOCAL_DATE, object.getIsoLocalDate());
    }

    @Test
    @DisplayName("LocalTime conversion object to json")
    public void localTimeObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setIsoLocalTime(LOCAL_TIME);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("14:22:33.025", doc.read("$.isoLocalTime", String.class));
    }

    @Test
    @DisplayName("LocalTime conversion json to object")
    public void localTimeJsonToObject() throws IOException {
        String json = "{\"isoLocalTime\":\"14:22:33.025\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(LOCAL_TIME, object.getIsoLocalTime());
    }

    @Test
    @DisplayName("Custom DateTime conversion object to json")
    public void customDateTimeObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setCustomDateTime(DATE_TIME);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("01/01/2018 14_22_33_025|UTC", doc.read("$.customDateTime", String.class));
    }

    @Test
    @DisplayName("Custom DateTime conversion json to object")
    public void customdateTimeJsonToObject() throws IOException {
        String json = "{\"customDateTime\":\"01/01/2018 14_22_33_025|UTC\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(DATE_TIME, object.getCustomDateTime());
    }

    // FIXME failing test, see https://github.com/FasterXML/jackson-datatype-joda/issues/101
    @Test
    @DisplayName("Custom Instant conversion object to json")
    public void customInstantObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setCustomInstant(INSTANT);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("01/01/2018 14_22_33_025|UTC", doc.read("$.customInstant", String.class));
    }

    // FIXME failing test, see https://github.com/FasterXML/jackson-datatype-joda/issues/101
    @Test
    @DisplayName("Custom Instant conversion json to object")
    public void customInstantJsonToObject() throws IOException {
        String json = "{\"customInstant\":\"01/01/2018 14_22_33_025|UTC\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(INSTANT, object.getCustomInstant());
    }

    // FIXME failing test, see https://github.com/FasterXML/jackson-datatype-joda/issues/101
    @Test
    @DisplayName("Custom YearMonth conversion object to json")
    public void customYearMonthObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setCustomYearMonth(YEAR_MONTH);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("2018/01", doc.read("$.customYearMonth", String.class));
    }

    // FIXME failing test, see https://github.com/FasterXML/jackson-datatype-joda/issues/101
    @Test
    @DisplayName("Custom YearMonth conversion json to object")
    public void customYearMonthJsonToObject() throws IOException {
        String json = "{\"customYearMonth\":\"2018/01\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(YEAR_MONTH, object.getCustomYearMonth());
    }

    // FIXME failing test, see https://github.com/FasterXML/jackson-datatype-joda/issues/101
    @Test
    @DisplayName("Custom MonthDay conversion object to json")
    public void customMonthDayObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setCustomMonthDay(MONTH_DAY);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("01_14", doc.read("$.customMonthDay", String.class));
    }

    // FIXME failing test, see https://github.com/FasterXML/jackson-datatype-joda/issues/101
    @Test
    @DisplayName("Custom MonthDay conversion json to object")
    public void customMonthDayJsonToObject() throws IOException {
        String json = "{\"customMonthDay\":\"01_14\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(MONTH_DAY, object.getCustomMonthDay());
    }

    @Test
    @DisplayName("Custom LocalDateTime conversion object to json")
    public void customLocalDateTimeObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setCustomLocalDateTime(LOCAL_DATE_TIME);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("01/01/2018 14_22_33_025", doc.read("$.customLocalDateTime", String.class));
    }

    @Test
    @DisplayName("Custom LocalDateTime conversion json to object")
    public void customLocalDateTimeJsonToObject() throws IOException {
        String json = "{\"customLocalDateTime\":\"01/01/2018 14_22_33_025\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(LOCAL_DATE_TIME, object.getCustomLocalDateTime());
    }

    @Test
    @DisplayName("Custom LocalDate conversion object to json")
    public void customLocalDateObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setCustomLocalDate(LOCAL_DATE);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("01/01/2018", doc.read("$.customLocalDate", String.class));
    }

    @Test
    @DisplayName("Custom LocalDate conversion json to object")
    public void customLocalDateJsonToObject() throws IOException {
        String json = "{\"customLocalDate\":\"01/01/2018\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(LOCAL_DATE, object.getCustomLocalDate());
    }

    @Test
    @DisplayName("Custom LocalTime conversion object to json")
    public void customLocalTimeObjectToJson() throws JsonProcessingException {
        JodaTime object = new JodaTime();
        object.setCustomLocalTime(LOCAL_TIME);
        String json = toJson(object);
        DocumentContext doc = toDoc(json);
        assertEquals("14_22_33_025", doc.read("$.customLocalTime", String.class));
    }

    @Test
    @DisplayName("Custom LocalTime conversion json to object")
    public void customLocalTimeJsonToObject() throws IOException {
        String json = "{\"customLocalTime\":\"14_22_33_025\"}";
        JodaTime object = toObject(json, JodaTime.class);
        assertEquals(LOCAL_TIME, object.getCustomLocalTime());
    }

}
