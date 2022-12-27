package com.wisercat.bestfriend.service.pets_service.get_use_case;

import com.wisercat.bestfriend.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PetsGetRepository extends PagingAndSortingRepository<Pet, Integer> {
    Optional<Pet> getById(Long id);

    Page<Pet> getAllByOwnerUsername(String username, Pageable pageable);

    Optional<Pet> getPetByIdAndOwnerUsername(Long id, String ownerUsername);
}

