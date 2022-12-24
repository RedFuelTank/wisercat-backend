package com.wisercat.bestfriend.controller.pets_controller.get_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;

import java.util.List;

public interface PetsGetService {
    PetDto getById(Long id);

    List<PetDto> getAll();

    PetDto getPetByIdByUsername(String username, Long id);

    List<PetDto> getUserPetsByPages(String username, int page, int size);
}
