package com.wisercat.bestfriend.requestbuilder.pets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wisercat.bestfriend.dto.pet.PetDto;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.wisercat.bestfriend.config.WebTestConfig.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PetsPostRequestBuilder {

    private MockMvc mockMvc;

    public PetsPostRequestBuilder(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions save(PetDto petDto) throws Exception {
        return mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(petDto)));
    }
    private byte[] convertObjectToJsonBytes(Object input) throws JsonProcessingException {
        return getObjectMapper().writeValueAsBytes(input);
    }
}
