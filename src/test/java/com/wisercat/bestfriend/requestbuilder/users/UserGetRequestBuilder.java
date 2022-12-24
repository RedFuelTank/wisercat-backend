package com.wisercat.bestfriend.requestbuilder.users;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class UserGetRequestBuilder {
    private MockMvc mockMvc;

    public UserGetRequestBuilder(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions getUserByUsername(String username) throws Exception {
        return mockMvc
                .perform(get("/users/{username}", username));
    }

    public ResultActions getUserPetsByPages(String username, int page, int size) throws Exception {
        return mockMvc
                .perform(get("/users/{username}/pets?page={page}&size={size}", username, page, size));
    }

    public ResultActions getPetByIdByUsername(String username, Long id) throws Exception {
        return mockMvc
                .perform(get("/users/{username}/pets/{id}", username, id));
    }
}
