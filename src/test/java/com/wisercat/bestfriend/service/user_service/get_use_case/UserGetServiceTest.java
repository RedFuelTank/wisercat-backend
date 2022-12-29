package com.wisercat.bestfriend.service.user_service.get_use_case;

import com.wisercat.bestfriend.dto.user.UserDto;
import com.wisercat.bestfriend.exception.NotFoundException;
import com.wisercat.bestfriend.model.User;
import com.wisercat.bestfriend.service.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.wisercat.bestfriend.config.user.WebUserTestFactory.*;
import static com.wisercat.bestfriend.custom_assert.UserDtoAssert.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserGetServiceTest {
    private UserGetRepository repository;
    private UserGetServiceImpl service;
    private Mapper<UserDto, User> mapper;

    @BeforeEach
    void init() {
        repository = mock(getUserGetRepositoryImpl().getClass());
        mapper = mock(getUserMapperImpl().getClass());
        service = new UserGetServiceImpl(repository, mapper);
    }

    @Nested
    @DisplayName("When need to get data about specific user by username")
    class GetUserByUsername {
        private final static String USER_USERNAME = "user";

        @Nested
        @DisplayName("When user with username exists in database")
        class UserExists {
            @BeforeEach
            void init() {
                given(repository.getUserByUsername(USER_USERNAME))
                        .willReturn(Optional.of(new User(USER_USERNAME)));
            }

            @Test
            @DisplayName("Should return non-null value")
            void shouldReturnNonNullValue() {
                assertThat(service.getUserByUsername(USER_USERNAME))
                        .isNotNull();
            }

            @Test
            @DisplayName("Should return user correct data")
            void shouldReturnCorrectData() {
                assertThat(service.getUserByUsername(USER_USERNAME))
                        .hasUsername();
            }
        }

        @Nested
        @DisplayName("When user with username isn't exist in database")
        class UserDoesNotExist {

            private static final String USER_NOT_FOUND_EXCEPTION_MESSAGE =
                    String.format("User with username (%s) does not exist", USER_USERNAME);

            @BeforeEach
            void init() {
                given(repository.getUserByUsername(USER_USERNAME))
                        .willReturn(Optional.empty());
            }

            @Test
            @DisplayName("Should throw exception with correct data")
            void shouldReturnException() {
                assertThatThrownBy(() -> service.getUserByUsername(USER_USERNAME))
                        .isInstanceOf(NotFoundException.class)
                        .hasMessage(USER_NOT_FOUND_EXCEPTION_MESSAGE);
            }
        }
    }
}