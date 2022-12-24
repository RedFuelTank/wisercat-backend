package com.wisercat.bestfriend.requestbuilder.pets;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


public class PetsGetRequestBuilder {
    private MockMvc mockMvc;

    public PetsGetRequestBuilder(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions getById(Long id) throws Exception {
        return mockMvc.perform(get("/pets/{id}", id));
    }

    public ResultActions getAll() throws Exception {
        return mockMvc.perform(get("/pets"));
    }

    public ResultActions getUserPetsByPages(String username, int page, int size) throws Exception {
        return mockMvc
                .perform(get("/{username}/pets?page={page}&size={size}", username, page, size));
    }

    public ResultActions getPetByIdByUsername(String username, Long id) throws Exception {
        return mockMvc
                .perform(get("/{username}/pets/{id}", username, id));
    }
}
