package com.wisercat.bestfriend.controller.pets_controller.add_use_case;

import com.wisercat.bestfriend.dto.PetDto;

public interface PetsAddUseCase {
    PetDto save(PetDto pet);
}
