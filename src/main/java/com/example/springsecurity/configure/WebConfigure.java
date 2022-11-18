package com.example.springsecurity.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfigure {
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilder() {
    return builder -> {
      AfterburnerModule abm = new AfterburnerModule();
      JavaTimeModule jtm = new JavaTimeModule();
      jtm.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));

      builder.visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
      builder.visibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
      builder.visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
      builder.serializationInclusion(JsonInclude.Include.NON_NULL);
      builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      builder.modulesToInstall(abm, jtm);

    };
  }
}
