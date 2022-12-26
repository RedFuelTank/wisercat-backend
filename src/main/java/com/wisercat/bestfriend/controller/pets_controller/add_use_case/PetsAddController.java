package com.wisercat.bestfriend.controller.pets_controller.add_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PetsAddController {
    private final PetsAddService service;
    @PostMapping("/pets")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#petDto.getOwnerUsername() == authentication.name")
    public PetDto save(@RequestBody @Valid PetDto petDto) {
        return service.save(petDto);
    }
}
