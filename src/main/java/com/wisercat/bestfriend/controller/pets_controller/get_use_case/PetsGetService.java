package com.wisercat.bestfriend.controller.pets_controller.get_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PetsGetService {
    PetDto getById(Long id);

    PetDto getPetByIdByUsername(String username, Long id);

    List<PetDto> getAll(Pageable pageable);

    List<PetDto> getUserPetsByPages(String username, Pageable pageable);
}
