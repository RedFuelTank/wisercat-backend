package com.wisercat.bestfriend.service.mapper.user;

import com.wisercat.bestfriend.dto.user.UserDto;
import com.wisercat.bestfriend.model.User;
import com.wisercat.bestfriend.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDto, User> {
    @Override
    public UserDto toDto(User user) {
        return new UserDto(user.getUsername());
    }

    @Override
    public User toEntity(UserDto userDto) {
        return new User(userDto.getUsername());
    }
}
