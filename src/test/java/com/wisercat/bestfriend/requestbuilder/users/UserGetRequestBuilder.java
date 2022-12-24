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
}
