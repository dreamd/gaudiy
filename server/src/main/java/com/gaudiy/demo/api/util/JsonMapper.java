package com.gaudiy.demo.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unchecked")
@Component
public class JsonMapper {

  private final ObjectMapper mapper;

  public JsonMapper() {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.registerModule(new SimpleModule());
  }

  public String write(Object o) throws JsonProcessingException {
    return mapper.writeValueAsString(o);
  }

  public <T> T read(String jsonString, TypeReference<T> typeReference) throws JsonProcessingException {
    return mapper.readValue(jsonString, typeReference);
  }
}
