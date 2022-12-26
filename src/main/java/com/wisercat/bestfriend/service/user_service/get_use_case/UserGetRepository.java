package com.wisercat.bestfriend.service.user_service.get_use_case;

import com.wisercat.bestfriend.dto.user.UserDto;
import com.wisercat.bestfriend.model.User;

import java.util.Optional;

public interface UserGetRepository {
    Optional<User> getUserByUsername(String userUsername);
}
