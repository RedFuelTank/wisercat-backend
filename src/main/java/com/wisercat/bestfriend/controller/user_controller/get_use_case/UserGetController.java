package com.wisercat.bestfriend.controller.user_controller.get_use_case;

import com.wisercat.bestfriend.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserGetController {
    private final UserGetService service;

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return service.getUserByUsername(username);
    }

}
