package com.wisercat.bestfriend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisercat.bestfriend.controller.pets_controller.add_use_case.PetsAddService;
import com.wisercat.bestfriend.controller.pets_controller.get_use_case.PetsGetService;
import com.wisercat.bestfriend.dto.PetDto;
import com.wisercat.bestfriend.exception.handler.ExceptionsHandler;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

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

    public static PetsGetService getPetsGetUseCaseImpl() {
        return new PetsGetService() {
            @Override
            public PetDto getById(Long id) {
                return null;
            }

            @Override
            public List<PetDto> getAll() {
                return null;
            }
        };
    }

    public static PetsAddService getPetsAddUseCaseImpl() {
        return new PetsAddService() {
            @Override
            public PetDto save(PetDto pet) {
                return null;
            }
        };
    }
}
