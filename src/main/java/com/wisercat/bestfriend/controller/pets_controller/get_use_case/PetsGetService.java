package com.wisercat.bestfriend.controller.pets_controller.get_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PetsGetService {
    PetDto getById(Long id);

    PetDto getPetByIdByUsername(String username, Long id);

    Page<PetDto> getAll(Pageable pageable);

    Page<PetDto> getUserPetsByPages(String username, Pageable pageable);
}
