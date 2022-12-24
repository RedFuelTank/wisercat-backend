package com.wisercat.bestfriend.service.user_service.get_use_case;

import com.wisercat.bestfriend.controller.user_controller.get_use_case.UserGetService;
import com.wisercat.bestfriend.dto.user.UserDto;
import com.wisercat.bestfriend.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserGetServiceImpl implements UserGetService {
    private final UserGetRepository repository;

    @Override
    public UserDto getUserByUsername(String username) {
        return repository.getUserByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        String.format("User with username (%s) does not exist", username)
                ));
    }

}
