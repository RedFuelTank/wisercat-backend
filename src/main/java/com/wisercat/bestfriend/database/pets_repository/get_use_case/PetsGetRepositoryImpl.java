package com.wisercat.bestfriend.database.pets_repository.get_use_case;

import com.wisercat.bestfriend.dto.PetDto;
import com.wisercat.bestfriend.service.pets_service.get_use_case.PetsGetRepository;

import java.util.List;
import java.util.Optional;

public class PetsGetRepositoryImpl implements PetsGetRepository {

    @Override
    public Optional<PetDto> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PetDto> getAll() {
        return null;
    }
}
