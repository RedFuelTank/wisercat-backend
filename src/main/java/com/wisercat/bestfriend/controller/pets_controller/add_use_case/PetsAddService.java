package com.wisercat.bestfriend.controller.pets_controller.add_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.dto.pet.RegistrationPetDto;

public interface PetsAddService {
    PetDto save(RegistrationPetDto pet, String username);
}
