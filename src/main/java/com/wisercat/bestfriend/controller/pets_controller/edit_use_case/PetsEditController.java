package com.wisercat.bestfriend.controller.pets_controller.edit_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PetsEditController {
    private final PetsEditService service;

    @PutMapping("/{username}/pets")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#petDto.ownerUsername == authentication.name or hasRole('ROLE_ADMIN')")
    public PetDto edit(@RequestBody @Valid PetDto petDto) {
        return service.edit(petDto);
    }


}
