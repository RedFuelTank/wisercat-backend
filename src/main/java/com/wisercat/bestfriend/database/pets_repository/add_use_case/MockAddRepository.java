package com.wisercat.bestfriend.database.pets_repository.add_use_case;

import com.wisercat.bestfriend.exception.DataAlreadyExistsException;
import com.wisercat.bestfriend.model.Pet;
import com.wisercat.bestfriend.service.pets_service.add_use_case.PetsAddRepository;

public class MockAddRepository implements PetsAddRepository {
    private long nextId;

    @Override
    public Pet save(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(nextId++);
            return pet;
        } else {
            throw new DataAlreadyExistsException("This data is already exist in database");
        }
    }
}
