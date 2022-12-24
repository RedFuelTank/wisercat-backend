package com.wisercat.bestfriend.custom_assert;

import com.wisercat.bestfriend.dto.user.UserDto;
import org.assertj.core.api.*;

import java.util.List;

public class UserDtoAssert extends AbstractAssert<UserDtoAssert, UserDto> {

    protected UserDtoAssert(UserDto userDto) {
        super(userDto, UserDtoAssert.class);
    }

    public static UserDtoAssert assertThat(UserDto userDto) {
        return new UserDtoAssert(userDto);
    }

    public UserDtoAssert hasUsername() {
        isNotNull();
        if (actual.getUsername() == null) {
            failWithMessage(
                    "Expected user dto to have username, but it was null"
            );
        }
        return this;
    }
}
