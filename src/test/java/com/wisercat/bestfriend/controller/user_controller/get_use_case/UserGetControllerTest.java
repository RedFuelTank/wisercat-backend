package com.wisercat.bestfriend.controller.user_controller.get_use_case;

import com.wisercat.bestfriend.dto.user.UserDto;
import com.wisercat.bestfriend.exception.NotFoundException;
import com.wisercat.bestfriend.requestbuilder.users.UserGetRequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.wisercat.bestfriend.config.WebTestConfig.getExceptionHandler;
import static com.wisercat.bestfriend.config.WebTestConfig.getObjectMapperHttpMessageConverter;
import static com.wisercat.bestfriend.config.user.WebUserTestFactory.getUserGetServiceImpl;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserGetControllerTest {
    private UserGetService service;
    private UserGetRequestBuilder requestBuilder;

    @BeforeEach
    void init() {
        service = mock(getUserGetServiceImpl().getClass());
        UserGetController controller = new UserGetController(service);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(getExceptionHandler())
                .setMessageConverters(getObjectMapperHttpMessageConverter())
                .build();
        requestBuilder = new UserGetRequestBuilder(mockMvc);
    }

    @Nested
    @DisplayName("Display information about user")
    class GetUserByUsername {
        private static final String USER_USERNAME = "admin";

        @Nested
        @DisplayName("User with specific username exists in database")
        class UserIsExist {
            private static UserDto mockUser;

            @BeforeEach
            void init() {
                mockUser = new UserDto(
                        USER_USERNAME
                );
                given(service.getUserByUsername(USER_USERNAME)).willReturn(mockUser);
            }

            @Test
            @DisplayName("Should return HTTP response code 200")
            void shouldReturnHttpResponseOk() throws Exception {
                requestBuilder.getUserByUsername(USER_USERNAME)
                        .andExpect(status().isOk());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                requestBuilder.getUserByUsername(USER_USERNAME)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            @DisplayName("Should return HTTP response with correct data of user")
            void shouldReturnHttpResponseCorrectData() throws Exception {
                requestBuilder.getUserByUsername(USER_USERNAME)
                        .andExpect(jsonPath("$.username", equalTo(USER_USERNAME)));
            }
        }

        @Nested
        @DisplayName("User with specific username doesn't exist in database")
        class UserIsNotExist {

            private static final String NOT_FOUND_EXCEPTION_MESSAGE =
                    String.format(
                            "User with username (%s) doesn't exist",
                            USER_USERNAME
                    );

            @BeforeEach
            void init() {
                given(service.getUserByUsername(USER_USERNAME))
                        .willThrow(new NotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
            }

            @Test
            @DisplayName("Should return HTTP response code 404")
            void shouldReturnHttpResponseCodeNotFound() throws Exception {
                requestBuilder.getUserByUsername(USER_USERNAME)
                        .andExpect(status().isNotFound());
            }

            @Test
            @DisplayName("Should return HTTP response with JSON media-type")
            void shouldReturnHttpResponseJsonMediaType() throws Exception {
                requestBuilder.getUserByUsername(USER_USERNAME)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            @DisplayName("Should return HTTP response with correct body")
            void shouldReturnHttpResponseEmptyBody() throws Exception {
                requestBuilder.getUserByUsername(USER_USERNAME)
                        .andExpect(jsonPath("$.name", equalTo(NotFoundException.class.getSimpleName())))
                        .andExpect(jsonPath("$.message", equalTo(NOT_FOUND_EXCEPTION_MESSAGE)));
            }
        }


    }
}