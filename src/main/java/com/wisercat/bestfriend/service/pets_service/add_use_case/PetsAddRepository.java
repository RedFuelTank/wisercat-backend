package com.wisercat.bestfriend.service.pets_service.add_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import com.wisercat.bestfriend.model.Pet;
import org.springframework.data.jpa.repository.Query;

public interface PetsAddRepository {
    Pet save(Pet pet);
}
