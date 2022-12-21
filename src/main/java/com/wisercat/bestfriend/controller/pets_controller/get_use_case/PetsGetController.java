package com.wisercat.bestfriend.controller.pets_controller.get_use_case;

import com.wisercat.bestfriend.dto.PetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetsGetController {
    private final PetsGetService service;
    @GetMapping("/pets/{id}")
    public PetDto findById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/pets")
    public List<PetDto> findAll() {
        return service.getAll();
    }
}
