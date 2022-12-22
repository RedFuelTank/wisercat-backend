package com.wisercat.bestfriend.service.pets_service.get_use_case;

import com.wisercat.bestfriend.dto.PetDto;

import java.util.List;
import java.util.Optional;

public interface PetsGetRepository {
    Optional<PetDto> getById(Long id);

    List<PetDto> getAll();
}

