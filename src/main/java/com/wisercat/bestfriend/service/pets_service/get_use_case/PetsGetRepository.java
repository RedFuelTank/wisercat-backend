package com.wisercat.bestfriend.service.pets_service.get_use_case;

import com.wisercat.bestfriend.dto.pet.PetDto;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PetsGetRepository extends PagingAndSortingRepository<PetDto, Integer> {
    Optional<PetDto> getById(Long id);

    List<PetDto> getAll();

    List<PetDto> getAllByOwnerUsername(String username, Pageable pageable);

}

