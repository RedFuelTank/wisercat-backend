package com.wisercat.bestfriend.controller.pets_controller.edit_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PetsEditController {
    private final PetsEditService service;

    @PutMapping("/{username}/pets")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#petDto.ownerUsername == authentication.name or hasRole('ROLE_ADMIN')")
    public PetDto edit(@RequestBody @Valid PetDto petDto, @PathVariable String username) {
        return service.edit(petDto);
    }


}
