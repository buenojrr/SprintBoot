package com.api.parkingcontrol.config;

import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

//Classe de configuração do Spring, precisa anotar ela como @Configuration porque quando a aplicação for inicializada
//o sistema vai levar em conta essas configurações customizadas

@Configuration
public class DateConfig {

  public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(
      DateTimeFormatter.ofPattern(DATETIME_FORMAT));

  // Como ObjectMapper é uma classe externa da aplicação para que o Spring entenda
  // a configuração e transforme em um Bean
  // Precisamos anotar o @Bean e @Primary por questoes de prioridade caso seja
  // gerado outros Beans para ObjectMapper.
  @Bean // metodo produtor
  @Primary
  public ObjectMapper objectMapper() {

    JavaTimeModule module = new JavaTimeModule();
    module.addSerializer(LOCAL_DATETIME_SERIALIZER);
    return new ObjectMapper()
        .registerModule(module);

  }

}
