package com.wisercat.bestfriend.controller.pets_controller.add_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.dto.pet.RegistrationPetDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PetsAddController {
    private final PetsAddService service;
    @PostMapping("{username}/pets")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#username == authentication.name")
    public PetDto save(@RequestBody @Valid RegistrationPetDto petDto, @PathVariable String username) {
        return service.save(petDto, username);
    }
}
