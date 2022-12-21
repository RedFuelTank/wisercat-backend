package com.wisercat.bestfriend.controller.pets_controller.get_use_case;

import com.wisercat.bestfriend.dto.PetDto;

import java.util.List;

public interface PetsGetService {
    PetDto getById(Long id);

    List<PetDto> getAll();
}
