package com.wisercat.bestfriend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wisercat.bestfriend.dto.PetDto;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.wisercat.bestfriend.config.WebTestConfig.getObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PostRequestBuilder {

    private MockMvc mockMvc;

    public PostRequestBuilder(MockMvc mockMvc) {
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
