package com.wisercat.bestfriend.controller.pets_controller.delete_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PetsDeleteController {
    private final PetsDeleteService service;
    @DeleteMapping("/{username}/pets/{id}")
    @PreAuthorize("#username == authentication.name or hasRole('ROLE_ADMIN')")
    public PetDto delete(@PathVariable String username, @PathVariable Long id) {
        return service.delete(username, id);
    }

}
