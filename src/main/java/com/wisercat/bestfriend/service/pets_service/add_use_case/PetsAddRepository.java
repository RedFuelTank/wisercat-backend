package com.wisercat.bestfriend.service.pets_service.add_use_case;

import com.wisercat.bestfriend.dto.PetDto;

public interface PetsAddRepository {
    PetDto save(PetDto petDto);
}
