package com.wisercat.bestfriend.controller.user_controller.get_use_case;

import com.wisercat.bestfriend.dto.user.UserDto;

public interface UserGetService {

    UserDto getUserByUsername(String username);
}
