package com.wisercat.bestfriend.database.pets_repository.add_use_case;

import com.wisercat.bestfriend.dto.PetDto;
import com.wisercat.bestfriend.exception.DataAlreadyExistsException;
import com.wisercat.bestfriend.service.pets_service.add_use_case.PetsAddRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PetsAddRepositoryImpl implements PetsAddRepository {
    private long nextId;
    @Override
    public PetDto save(PetDto petDto) {
        if (petDto.getId() == null) {
            petDto.setId(nextId++);
            return petDto;
        } else {
            throw new DataAlreadyExistsException("This data is already exist in database");
        }
    }
}
