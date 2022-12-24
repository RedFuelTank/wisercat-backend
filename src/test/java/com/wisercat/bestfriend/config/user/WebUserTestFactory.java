package com.wisercat.bestfriend.config.user;

import com.wisercat.bestfriend.controller.user_controller.get_use_case.UserGetService;
import com.wisercat.bestfriend.dto.user.UserDto;
import com.wisercat.bestfriend.service.user_service.get_use_case.UserGetRepository;

import java.util.Optional;

public class WebUserTestFactory {

    public static UserGetService getUserGetServiceImpl() {
        return new UserGetService() {
            @Override
            public UserDto getUserByUsername(String username) {
                return null;
            }

        };
    }

    public static UserGetRepository getUserGetRepositoryImpl() {
        return new UserGetRepository() {
            @Override
            public Optional<UserDto> getUserByUsername(String userUsername) {
                return null;
            }
        };
    }
}
