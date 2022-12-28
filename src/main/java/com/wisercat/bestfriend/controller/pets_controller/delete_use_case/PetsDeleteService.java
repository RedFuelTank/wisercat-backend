package com.wisercat.bestfriend.controller.pets_controller.delete_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;

public interface PetsDeleteService {
    PetDto delete(String username, Long id);
}
