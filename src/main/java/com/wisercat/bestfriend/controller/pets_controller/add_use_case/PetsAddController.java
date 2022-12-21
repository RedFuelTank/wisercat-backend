package com.wisercat.bestfriend.controller.pets_controller.add_use_case;

import com.wisercat.bestfriend.dto.PetDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PetsAddController {
    private final PetsAddService service;
    @PostMapping("/pets")
    @ResponseStatus(HttpStatus.CREATED)
    public PetDto save(@RequestBody @Valid PetDto petDto) {
        return service.save(petDto);
    }
}
