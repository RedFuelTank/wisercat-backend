package com.wisercat.bestfriend.service.pets_service.get_use_case;

import com.wisercat.bestfriend.dto.PetDto;

import java.util.List;

public interface PetsGetUseCase {
    PetDto getById(Long id);

    List<PetDto> getAll();
}
