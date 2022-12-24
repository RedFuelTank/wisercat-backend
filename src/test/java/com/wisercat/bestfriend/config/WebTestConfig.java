package com.wisercat.bestfriend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisercat.bestfriend.exception.handler.ExceptionsHandler;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class WebTestConfig {
    public static MappingJackson2HttpMessageConverter getObjectMapperHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    public static Object getExceptionHandler() {
        return new ExceptionsHandler();
    }
}
