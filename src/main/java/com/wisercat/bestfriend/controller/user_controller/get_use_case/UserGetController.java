package com.wisercat.bestfriend.controller.user_controller.get_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.dto.user.UserDto;
import com.wisercat.bestfriend.exception.InvalidParameterException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserGetController {
    private final UserGetService service;

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return service.getUserByUsername(username);
    }

    @GetMapping("/{username}/pets/{id}")
    public PetDto getPetByIdByUsername(@PathVariable String username, @PathVariable Long id) {
        return service.getPetByIdByUsername(username, id);
    }

    @GetMapping("/{username}/pets")
    public List<PetDto> getUserPetsByPages(@PathVariable String username,
                                           @RequestParam int page,
                                           @RequestParam int size) {
        validateData(username, page, size);
        return service.getUserPetsByPages(username, page, size);
    }

    private void validateData(String username, int page, int size) {
        validateUsername(username);
        validatePage(page);
        validateSize(size);
    }


    private void validateSize(int size) {
        if (size <= 0) {
            throw new InvalidParameterException(
                    String.format("Page size (%s) is unacceptable value", size)
            );
        }
    }

    private void validatePage(int page) {
        if (page <= 0) {
            throw new InvalidParameterException(
                    String.format("Page number (%s) is unacceptable value", page)
            );
        }
    }

    private void validateUsername(String username) {
        if (username.isEmpty() || username.isBlank()) {
            throw new InvalidParameterException(
                    String.format("Username (%s) is unacceptable value", username)
            );
        }
    }


}
