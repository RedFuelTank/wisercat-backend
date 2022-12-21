package com.wisercat.bestfriend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisercat.bestfriend.exception.handler.ExceptionsHandler;
import com.wisercat.bestfriend.service.pets_service.get_use_case.PetsGetService;
import com.wisercat.bestfriend.service.pets_service.get_use_case.PetsGetUseCase;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public final class WebTestConfig {
    private WebTestConfig() {}

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

    public static PetsGetUseCase getPetsGetUseCaseImpl() {
        return new PetsGetService();
    }
}
