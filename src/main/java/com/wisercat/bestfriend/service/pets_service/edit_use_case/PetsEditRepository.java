package com.wisercat.bestfriend.service.pets_service.edit_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.model.Pet;

public interface PetsEditRepository {
    Pet edit(Pet pet);
}
