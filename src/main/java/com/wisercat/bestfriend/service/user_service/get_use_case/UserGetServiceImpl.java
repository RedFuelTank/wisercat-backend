package com.wisercat.bestfriend.service.user_service.get_use_case;

import com.wisercat.bestfriend.controller.user_controller.get_use_case.UserGetService;
import com.wisercat.bestfriend.dto.user.UserDto;
import com.wisercat.bestfriend.exception.NotFoundException;
import com.wisercat.bestfriend.model.User;
import com.wisercat.bestfriend.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGetServiceImpl implements UserGetService {
    private final UserGetRepository repository;
    private final Mapper<UserDto, User> mapper;

    @Override
    public UserDto getUserByUsername(String username) {
        return mapper.toDto(repository.getUserByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        String.format("User with username (%s) doesn't exist", username))));
    }

}
