package com.wisercat.bestfriend.config.pets;

import com.wisercat.bestfriend.controller.pets_controller.get_use_case.PetsGetService;
import com.wisercat.bestfriend.database.pets_repository.get_use_case.MockGetRepository;
import com.wisercat.bestfriend.service.pets_service.get_use_case.PetsGetRepository;
import com.wisercat.bestfriend.service.pets_service.get_use_case.PetsGetServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WebMvcPetsTestConfig {

    @Bean
    public PetsGetService getService(PetsGetRepository repository) {
        return new PetsGetServiceImpl(repository);
    }

    @Bean
    public PetsGetRepository getRepository() {
        return new MockGetRepository();
    }
}
