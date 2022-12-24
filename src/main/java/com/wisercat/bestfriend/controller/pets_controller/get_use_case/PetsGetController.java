package com.wisercat.bestfriend.controller.pets_controller.get_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.exception.InvalidParameterException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetsGetController {
    private final PetsGetService service;
    @GetMapping("/pets/{id}")
    @Secured("ROLE_ADMIN")
    public PetDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/pets")
    @Secured("ROLE_ADMIN")
    public List<PetDto> findAll() {
        return service.getAll();
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
