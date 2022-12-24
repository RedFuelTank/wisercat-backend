package com.wisercat.bestfriend.controller.pets_controller.add_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;

public interface PetsAddService {
    PetDto save(PetDto pet);
}
